package com.clinica.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Usuário é obrigatório")
    private String usuario;
    
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
} 