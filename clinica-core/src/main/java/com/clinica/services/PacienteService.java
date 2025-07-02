package com.clinica.services;

import com.clinica.dtos.PacienteRequestDTO;
import com.clinica.dtos.PacienteResponseDTO;
import com.clinica.models.Paciente;
import com.clinica.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.clinica.exception.ActionClinicaMedicaException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PacienteService {
    private final ModelMapper modelMapper;
    private final PacienteRepository pacienteRepository;

    public PacienteService(ModelMapper modelMapper, PacienteRepository pacienteRepository) {
        this.modelMapper = modelMapper;
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteResponseDTO cadastrarPaciente(PacienteRequestDTO pacienteRequestDTO) {
        log.info("Cadastro de paciente - service: {}", pacienteRequestDTO);
        Paciente paciente = modelMapper.map(pacienteRequestDTO, Paciente.class);
        paciente = pacienteRepository.save(paciente);
        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    public List<PacienteResponseDTO> listarPacientes() {
        log.info("Listando todos os pacientes");
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(paciente -> modelMapper.map(paciente, PacienteResponseDTO.class))
                .toList();
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        log.info("Buscando paciente com ID: {}", id);

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ActionClinicaMedicaException(
                        "Paciente não encontrado com ID: " + id));
        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    @Transactional
    public PacienteResponseDTO atualizarPaciente(Long id, PacienteRequestDTO pacienteRequestDTO) {
        log.info("Atualizando paciente com ID: {}", id);
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ActionClinicaMedicaException(
                        "Paciente não encontrado com ID: " + id));
        modelMapper.map(pacienteRequestDTO, pacienteExistente);
        Paciente pacienteAtualizado = pacienteRepository.save(pacienteExistente);
        return modelMapper.map(pacienteAtualizado, PacienteResponseDTO.class);
    }

    @Transactional
    public void excluirPaciente(Long id) {
        log.info("Excluindo paciente com ID: {}", id);
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ActionClinicaMedicaException(
                        "Paciente não encontrado para exclusão."));
        pacienteRepository.delete(paciente);
    }

}

