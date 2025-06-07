package com.clinica.services

import com.clinica.models.Consulta;
import com.clinica.repositories.ConsultaRepository;    
import jakarta.transaction.Transactional;
import med.voll.api.model.Consulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Transactional
    public Consulta agendarConsulta(Consulta consulta) {

        if (consulta.getDataHora().isBefore(LocalDateTime.now().plusHours(24))) {
            throw new IllegalArgumentException("Consultas devem ser agendadas com pelo menos 24h de antecedência.");
        }

        boolean conflito = consultaRepository.existsByMedicoIdAndDataHora(consulta.getMedicoId(), consulta.getDataHora());
        if (conflito) {
            throw new IllegalStateException("Médico já possui consulta nesse horário.");
        }

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    @Transactional
    public void cancelarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
