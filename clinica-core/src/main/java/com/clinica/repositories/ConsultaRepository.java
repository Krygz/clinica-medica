package com.clinica.repositories;

import com.clinica.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
