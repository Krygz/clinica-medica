package com.clinica.dtos;

import com.clinica.enums.Tipo;
import com.clinica.models.Especialidade;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

// depois usar uma annotation para requerir alguns dos dados
public record FuncionarioRequestDTO(
         @NotBlank(message = "Usuário é obrigatório")
         @Size(min = 3, max = 50, message = "Usuário deve ter entre 3 e 50 caracteres")
         String usuario,
         
         @NotBlank(message = "Senha é obrigatória")
         @Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
         String senha,
         
         @NotNull(message = "Idade é obrigatória")
         @Min(value = 18, message = "Idade mínima é 18 anos")
         @Max(value = 120, message = "Idade máxima é 120 anos")
         Integer idade,
         
         @NotNull(message = "Sexo é obrigatório")
         char sexo,
         
         @NotBlank(message = "CPF é obrigatório")
         @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
         String cpf,
         
         @NotBlank(message = "Rua é obrigatória")
         @Size(max = 100, message = "Rua deve ter no máximo 100 caracteres")
         String rua,
         
         @NotBlank(message = "Número é obrigatório")
         @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
         String numero,
         
         @Size(max = 50, message = "Complemento deve ter no máximo 50 caracteres")
         String complemento,
         
         @NotBlank(message = "Bairro é obrigatório")
         @Size(max = 50, message = "Bairro deve ter no máximo 50 caracteres")
         String bairro,
         
         @NotBlank(message = "Cidade é obrigatória")
         @Size(max = 50, message = "Cidade deve ter no máximo 50 caracteres")
         String cidade,
         
         @NotBlank(message = "Estado é obrigatório")
         @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
         String estado,
         
         @NotBlank(message = "Contato é obrigatório")
         @Size(max = 20, message = "Contato deve ter no máximo 20 caracteres")
         String contato,
         
         @NotBlank(message = "Email é obrigatório")
         @Email(message = "Email deve ser válido")
         @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
         String email,
         
         @NotNull(message = "Data de nascimento é obrigatória")
         LocalDateTime dataNascimento,
         
         Especialidade especialidade,
         
         @NotNull(message = "Tipo é obrigatório")
         Tipo tipo
) {
}
