package com.clinica.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AutenticadorService {

    private final FuncionarioService funcionarioService;
}