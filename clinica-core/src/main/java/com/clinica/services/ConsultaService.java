package com.clinica.services;

import com.clinica.dtos.ConsultaRequestDTO;
import com.clinica.dtos.ConsultaResponseDTO;
import com.clinica.models.Consulta;
import com.clinica.repositories.ConsultaRepository;    
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final ModelMapper modelMapper;

    public ConsultaService(ConsultaRepository consultaRepository, ModelMapper modelMapper) {
        this.consultaRepository = consultaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Consulta agendarConsulta(Consulta consulta) {
        if (consulta.getDataHora().isBefore(LocalDateTime.now().plusHours(24))) {
            throw new IllegalArgumentException("Consultas devem ser agendadas com pelo menos 24h de antecedência.");
        }

        boolean conflito = consultaRepository.existsByFuncionarioIdAndDataHora(consulta.getFuncionario().getId(), consulta.getDataHora());
        if (conflito) {
            throw new IllegalStateException("Médico já possui consulta nesse horário.");
        }

        return consultaRepository.save(consulta);
    }

    public ConsultaResponseDTO agendar(ConsultaRequestDTO consultaRequestDTO) {
        log.info("Agendando consulta: {}", consultaRequestDTO);
        
        Consulta consulta = modelMapper.map(consultaRequestDTO, Consulta.class);
        consulta.setEstaAtiva(true);
        
        Consulta consultaSalva = agendarConsulta(consulta);
        return modelMapper.map(consultaSalva, ConsultaResponseDTO.class);
    }

    public List<ConsultaResponseDTO> listarPorPaciente(Long pacienteId) {
        log.info("Listando consultas do paciente: {}", pacienteId);
        
        List<Consulta> consultas = consultaRepository.findByPacienteId(pacienteId);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelar(Long consultaId) {
        log.info("Cancelando consulta: {}", consultaId);
        
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada com ID: " + consultaId));
        
        consulta.setEstaAtiva(false);
        consultaRepository.save(consulta);
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
