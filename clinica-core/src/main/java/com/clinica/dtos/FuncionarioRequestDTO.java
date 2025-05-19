package com.clinica.dtos;

import com.clinica.enums.Tipo;
import com.clinica.models.Especialidade;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

// depois usar uma annotation para requerir alguns dos dados
public record FuncionarioRequestDTO(
         String usuario,
         Integer senha,
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
