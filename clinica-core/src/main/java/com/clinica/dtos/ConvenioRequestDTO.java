package com.clinica.dtos;

import com.clinica.models.Convenio;
import com.clinica.models.Funcionario;
import com.clinica.models.Paciente;
import com.clinica.models.Prontuario;

import java.time.LocalDateTime;

public record ConvenioRequestDTO(
         String nome,
         String descricao
){
}
