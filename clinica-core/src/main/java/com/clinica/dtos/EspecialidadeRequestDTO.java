package com.clinica.dtos;

import com.clinica.models.Convenio;
import com.clinica.models.Funcionario;
import com.clinica.models.Paciente;
import com.clinica.models.Prontuario;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EspecialidadeRequestDTO(
         @NotBlank(message = "Nome é obrigatório")
         @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
         String nome,
         
         @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
         String descricao
){
}
