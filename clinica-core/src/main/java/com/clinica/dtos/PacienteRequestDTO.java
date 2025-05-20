package com.clinica.dtos;


import com.clinica.models.Consulta;

import java.time.LocalDateTime;
import java.util.List;

public record PacienteRequestDTO(
        String nome,
        Integer idade,
        char sexo,
        String cpf,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String contato,
        String email,
        LocalDateTime dataNascimento,
        List<Consulta>consultas
){
}
