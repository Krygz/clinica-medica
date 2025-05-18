package com.clinica.repositories;

import com.clinica.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
