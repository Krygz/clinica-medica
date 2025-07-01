package com.clinica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoResponseDTO {
    private Long id;
    private String descricao;
    private LocalDateTime dataHora;
    private Long pacienteId;
    private Long medicoId;
}
