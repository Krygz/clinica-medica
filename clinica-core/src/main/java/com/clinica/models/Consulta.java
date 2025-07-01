package com.clinica.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;
    @ManyToOne
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    
    public Long getMedicoId() {
        return funcionario != null ? funcionario.getId() : null;
    }
}
