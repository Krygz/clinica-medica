package com.clinica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtendimentoResponseDTO {
    private Long id;
    private String descricao;
    private LocalDateTime dataHora;
    private Long pacienteId;
    private Long medicoId;

public Object getId() {
    return this.id;
    }
}
