package com.clinica.repositories;

import com.clinica.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
