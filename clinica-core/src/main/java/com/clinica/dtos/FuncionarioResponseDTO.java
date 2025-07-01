package com.clinica.dtos;

import com.clinica.enums.Tipo;
import com.clinica.models.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponseDTO {
    private Long id;
    private String usuario;
    private Integer idade;
    private char sexo;
    private String cpf;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String contato;
    private String email;
    private LocalDateTime dataNascimento;
    private Especialidade especialidade;
    private Tipo tipo;
}
