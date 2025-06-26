package com.clinica.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AutenticadorService {

    private final FuncionarioService funcionarioService;

    public AutenticadorService(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
}