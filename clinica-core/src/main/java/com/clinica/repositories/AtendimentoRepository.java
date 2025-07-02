package com.clinica.repositories;

import com.clinica.models.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByPacienteId(Long pacienteId);
    List<Atendimento> findByPacienteIdAndAtivoTrue(Long pacienteId);
    List<Atendimento> findByMedicoId(Long medicoId);
    List<Atendimento> findByMedicoIdAndAtivoTrue(Long medicoId);
    List<Atendimento> findByAtivoTrue();
    List<Atendimento> findByDataHoraBetweenAndAtivoTrue(LocalDateTime inicio, LocalDateTime fim);
} 