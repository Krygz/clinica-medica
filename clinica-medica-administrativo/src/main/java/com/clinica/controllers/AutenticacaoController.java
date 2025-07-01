package com.clinica.controllers;

import com.clinica.dtos.LoginRequestDTO;
import com.clinica.dtos.LoginResponseDTO;
import com.clinica.models.Funcionario;
import com.clinica.services.AutenticadorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AutenticadorService autenticadorService;

    public AutenticacaoController(AutenticadorService autenticadorService) {
        this.autenticadorService = autenticadorService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        log.info("Tentativa de login para usuário: {}", loginRequest.getUsuario());
        
        try {
            Funcionario funcionario = autenticadorService.autenticar(loginRequest.getUsuario(), loginRequest.getSenha());
            
            // Gerar token simples (em produção, usar JWT)
            String token = UUID.randomUUID().toString();
            
            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(token);
            response.setUsuario(funcionario.getUsuario());
            response.setTipo(funcionario.getTipo().toString());
            response.setMensagem("Login realizado com sucesso");
            
            log.info("Login bem-sucedido para usuário: {}", funcionario.getUsuario());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.warn("Falha no login para usuário: {} - {}", loginRequest.getUsuario(), e.getMessage());
            
            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(null);
            response.setUsuario(loginRequest.getUsuario());
            response.setTipo(null);
            response.setMensagem("Usuário ou senha inválidos");
            
            return ResponseEntity.badRequest().body(response);
        }
    }
} 