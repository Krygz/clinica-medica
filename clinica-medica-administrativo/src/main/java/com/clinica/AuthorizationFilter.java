package com.clinica;

import com.clinica.exception.ActionClinicaMedicaException;
import com.clinica.exception.AuthenticationClinicaMedicaException;
import com.clinica.models.Funcionario;
import com.clinica.services.AutenticadorService;
import com.clinica.services.AutorizadorService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AutenticadorService autenticadorService;
    private final AutorizadorService autorizadorService;

    public AuthorizationFilter(
            AutenticadorService autenticadorService,
            AutorizadorService autorizadorService
    ) {
        this.autenticadorService = autenticadorService;
        this.autorizadorService = autorizadorService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String path = request.getRequestURI();
            log.debug("Iniciando validação de acesso para a requisição: {}", path);
            
            // Verificar se é uma rota de documentação ou health check
            if (isPublicPath(path)) {
                log.debug("Acesso permitido para rota pública: {}", path);
                filterChain.doFilter(request, response);
                return;
            }
            
            // Validar autenticação e autorização
            validarAcessoAplicacao(request, response, filterChain);
            
        } catch (Exception e) {
            log.error("Erro ao processar requisição: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro interno do servidor");
        }
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/swagger-ui") || 
               path.startsWith("/v3/api-docs") || 
               path.startsWith("/actuator") ||
               path.startsWith("/auth") ||
               path.startsWith("/health") ||
               path.equals("/") ||
               path.equals("/favicon.ico");
    }

    private void validarAcessoAplicacao(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        log.debug("Iniciando validação de acesso para a aplicação");
        
        // Verificar se há token de autorização
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            // Autenticação por token (simplificada para teste)
            log.debug("Autenticação por token");
            String acao = Optional.ofNullable(request.getHeader("action"))
                    .orElse("READ"); // Ação padrão se não especificada
            
            try {
                // Em produção, validar o token JWT
                // Por enquanto, aceitar qualquer token válido
                log.debug("Token aceito, ação: {}", acao);
                filterChain.doFilter(request, response);
                return;
            } catch (Exception e) {
                log.warn("Token inválido: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            }
        }
        
        // Autenticação por headers (método original)
        String usuario = Optional.ofNullable(request.getHeader("usuario"))
                .orElseThrow(() -> new AuthenticationClinicaMedicaException("Usuário não encontrado"));
        
        String senha = Optional.ofNullable(request.getHeader("senha"))
                .orElseThrow(() -> new AuthenticationClinicaMedicaException("Senha não encontrada"));
        
        String acao = Optional.ofNullable(request.getHeader("action"))
                .orElseThrow(() -> new ActionClinicaMedicaException("Ação não encontrada"));

        try {
            // Autenticar usuário
            log.debug("Validando autenticação do usuário: {}", usuario);
            Funcionario funcionario = autenticadorService.autenticar(usuario, senha);
            
            // Autorizar ação
            log.debug("Validando autorização para ação: {}", acao);
            autorizadorService.autorizar(funcionario, acao);
            
            // Se chegou até aqui, a autenticação e autorização foram bem-sucedidas
            log.debug("Acesso autorizado para usuário: {} e ação: {}", usuario, acao);
            filterChain.doFilter(request, response);
            
        } catch (AuthenticationClinicaMedicaException e) {
            log.warn("Falha na autenticação: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Dados de acesso inválidos");
        } catch (ActionClinicaMedicaException e) {
            log.warn("Falha na autorização: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acesso negado");
        }
    }
} 