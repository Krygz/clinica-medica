package com.clinica.repositories;

import com.clinica.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByFuncionarioIdAndDataHora(Long funcionarioId, LocalDateTime dataHora);
    List<Consulta> findByPacienteId(Long pacienteId);
}
