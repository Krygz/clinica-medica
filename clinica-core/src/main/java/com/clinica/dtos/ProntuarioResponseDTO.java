package com.clinica.dtos;

public record ProntuarioResponseDTO(
    Integer id,
    String receituario,
    String exames,
    String observacao) {
}
