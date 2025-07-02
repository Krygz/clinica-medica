package com.clinica.services;

import com.clinica.dtos.AtendimentoRequestDTO;
import com.clinica.dtos.AtendimentoResponseDTO;
import com.clinica.dtos.AtendimentoUpdateDTO;
import com.clinica.models.Atendimento;
import com.clinica.models.Funcionario;
import com.clinica.models.Paciente;
import com.clinica.repositories.AtendimentoRepository;
import com.clinica.repositories.FuncionarioRepository;
import com.clinica.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final PacienteRepository pacienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ModelMapper modelMapper;

    public AtendimentoService(AtendimentoRepository atendimentoRepository,
                             PacienteRepository pacienteRepository,
                             FuncionarioRepository funcionarioRepository,
                             ModelMapper modelMapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public AtendimentoResponseDTO registrar(AtendimentoRequestDTO dto) {
        log.info("Registrando atendimento: {}", dto);
        
        // Buscar paciente e médico
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com ID: " + dto.getPacienteId()));
        
        Funcionario medico = funcionarioRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado com ID: " + dto.getMedicoId()));
        
        // Criar atendimento
        Atendimento atendimento = new Atendimento();
        atendimento.setDescricao(dto.getDescricao());
        atendimento.setDataHora(dto.getDataHora() != null ? dto.getDataHora() : LocalDateTime.now());
        atendimento.setPaciente(paciente);
        atendimento.setMedico(medico);
        atendimento.setAtivo(true);
        
        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        
        return converterParaResponseDTO(atendimentoSalvo);
    }

    public List<AtendimentoResponseDTO> listarPorPaciente(Long pacienteId) {
        log.info("Listando atendimentos do paciente: {}", pacienteId);
        
        List<Atendimento> atendimentos = atendimentoRepository.findByPacienteIdAndAtivoTrue(pacienteId);
        
        return atendimentos.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResponseDTO> listarPorMedico(Long medicoId) {
        log.info("Listando atendimentos do médico: {}", medicoId);
        
        List<Atendimento> atendimentos = atendimentoRepository.findByMedicoIdAndAtivoTrue(medicoId);
        
        return atendimentos.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelar(Long atendimentoId) {
        log.info("Cancelando atendimento: {}", atendimentoId);
        
        Atendimento atendimento = atendimentoRepository.findById(atendimentoId)
                .orElseThrow(() -> new IllegalArgumentException("Atendimento não encontrado com ID: " + atendimentoId));
        
        atendimento.setAtivo(false);
        atendimentoRepository.save(atendimento);
    }

    public List<AtendimentoResponseDTO> listarTodos() {
        log.info("Listando todos os atendimentos");
        
        List<Atendimento> atendimentos = atendimentoRepository.findAll();
        
        return atendimentos.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResponseDTO> listarAtivos() {
        log.info("Listando atendimentos ativos");
        
        List<Atendimento> atendimentos = atendimentoRepository.findByAtivoTrue();
        
        return atendimentos.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public AtendimentoResponseDTO buscarPorId(Long id) {
        log.info("Buscando atendimento por ID: {}", id);
        
        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Atendimento não encontrado com ID: " + id));
        
        return converterParaResponseDTO(atendimento);
    }

    @Transactional
    public AtendimentoResponseDTO atualizar(Long id, AtendimentoUpdateDTO dto) {
        log.info("Atualizando atendimento ID: {} com dados: {}", id, dto);
        
        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Atendimento não encontrado com ID: " + id));
        
        atendimento.setDescricao(dto.getDescricao());
        if (dto.getDataHora() != null) {
            atendimento.setDataHora(dto.getDataHora());
        }
        
        Atendimento atendimentoAtualizado = atendimentoRepository.save(atendimento);
        
        return converterParaResponseDTO(atendimentoAtualizado);
    }

    public List<AtendimentoResponseDTO> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        log.info("Buscando atendimentos entre {} e {}", inicio, fim);
        
        List<Atendimento> atendimentos = atendimentoRepository.findByDataHoraBetweenAndAtivoTrue(inicio, fim);
        
        return atendimentos.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    private AtendimentoResponseDTO converterParaResponseDTO(Atendimento atendimento) {
        AtendimentoResponseDTO dto = modelMapper.map(atendimento, AtendimentoResponseDTO.class);
        dto.setPacienteNome(atendimento.getPaciente().getNome());
        dto.setMedicoNome(atendimento.getMedico().getUsuario());
        return dto;
    }
} 