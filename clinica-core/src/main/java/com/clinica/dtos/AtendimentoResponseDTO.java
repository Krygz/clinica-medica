package com.clinica.dtos;

import java.time.LocalDateTime;

public record AtendimentoResponseDTO(
        Long id,
        String descricao,
        LocalDateTime dataHora,
        Long pacienteId,
        Long medicoId
) {}
