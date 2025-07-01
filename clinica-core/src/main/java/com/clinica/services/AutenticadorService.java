package com.clinica.services;

import com.clinica.exception.AuthenticationClinicaMedicaException;
import com.clinica.models.Funcionario;
import com.clinica.repositories.FuncionarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AutenticadorService {

    private final FuncionarioRepository funcionarioRepository;

    public AutenticadorService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    
    public Funcionario autenticar(String usuario, String senha) {
        log.info("Tentativa de autenticação para usuário: {}", usuario);
        
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByUsuario(usuario);
        
        if (funcionarioOpt.isEmpty()) {
            log.warn("Usuário não encontrado: {}", usuario);
            throw new AuthenticationClinicaMedicaException("Usuário ou senha inválidos");
        }
        
        Funcionario funcionario = funcionarioOpt.get();
        
        if (!senha.equals(funcionario.getSenha())) {
            log.warn("Senha incorreta para usuário: {}", usuario);
            throw new AuthenticationClinicaMedicaException("Usuário ou senha inválidos");
        }
        
        log.info("Autenticação bem-sucedida para usuário: {}", usuario);
        return funcionario;
    }
}