package com.clinica.dtos;

import com.clinica.enums.Tipo;
import com.clinica.models.Especialidade;

import java.time.LocalDateTime;

public record FuncionarioResponseDTO(
         Long id,
         String usuario,
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
//        @ManyToOne
//        @JoinColumn(name = "especialidade_id")
        Especialidade especialidade,
        Tipo tipo
) {
}
