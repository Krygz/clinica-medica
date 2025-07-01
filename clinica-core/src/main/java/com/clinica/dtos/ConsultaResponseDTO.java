package com.clinica.dtos;

import com.clinica.models.Convenio;
import com.clinica.models.Funcionario;
import com.clinica.models.Paciente;
import com.clinica.models.Prontuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaResponseDTO {
    private Long id;
    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;
    private Paciente paciente;
    private Prontuario prontuario;
    private Convenio convenio;
    private Funcionario funcionario;
}
