package com.clinica.dtos;

public record ProntuarioRequestDTO(
    String receituario,
    String exames,
    String observacao) {
}
