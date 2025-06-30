package com.clinica.dtos;

import java.time.LocalDateTime;

public record AtendimentoRequestDTO(
        String descricao,
        LocalDateTime dataHora,
        Long pacienteId,
        Long medicoId
) {}