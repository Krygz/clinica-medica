package com.clinica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioResponseDTO {
    private Integer id;
    private String receituario;
    private String exames;
    private String observacao;
}
