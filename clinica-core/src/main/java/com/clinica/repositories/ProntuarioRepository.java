package com.clinica.repositories;

import com.clinica.models.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
