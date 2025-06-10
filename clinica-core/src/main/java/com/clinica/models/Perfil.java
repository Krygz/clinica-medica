package com.clinica.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_perfil")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    // Permissões de Funcionario
    private boolean cadastrarFuncionario;
    private boolean lerFuncionario;
    private boolean atualizarFuncionario;
    private boolean deletarFuncionario;
    private boolean listarFuncionario;

    // Permissões de Paciente
    private boolean cadastrarPaciente;
    private boolean lerPaciente;
    private boolean atualizarPaciente;
    private boolean deletarPaciente;
    private boolean listarPaciente;

    // Permissões de Consulta
    private boolean cadastrarConsulta;
    private boolean lerConsulta;
    private boolean atualizarConsulta;
    private boolean deletarConsulta;
    private boolean listarConsulta;

    // Permissões de Especialidade
    private boolean cadastrarEspecialidade;
    private boolean lerEspecialidade;
    private boolean atualizarEspecialidade;
    private boolean deletarEspecialidade;
    private boolean listarEspecialidade;

    // Permissões de Convênio
    private boolean cadastrarConvenio;
    private boolean lerConvenio;
    private boolean atualizarConvenio;
    private boolean deletarConvenio;
    private boolean listarConvenio;

    // Permissões de Prontuário
    private boolean cadastrarProntuario;
    private boolean lerProntuario;
    private boolean atualizarProntuario;
    private boolean deletarProntuario;
    private boolean listarProntuario;
}
