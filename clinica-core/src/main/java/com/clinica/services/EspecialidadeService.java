package com.clinica.services;

import com.clinica.dtos.EspecialidadeRequestDTO;
import com.clinica.dtos.EspecialidadeResponseDTO;
import com.clinica.models.Especialidade;
import com.clinica.repositories.EspecialidadeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EspecialidadeService {

  private ModelMapper modelMapper;

  private EspecialidadeRepository especialidadeRepository;

  public EspecialidadeService(ModelMapper modelMapper, EspecialidadeRepository especialidadeRepository) {
    this.modelMapper = modelMapper;
    this.especialidadeRepository = especialidadeRepository;
  }

  // Implementação de métodos para manipulação de especialidades
  public EspecialidadeResponseDTO adicionarEspecialidade(EspecialidadeRequestDTO especialidadeRequest) {
    log.info("Cadadastro de especialidade - service: {}", especialidadeRequest);
    Especialidade especialidade = modelMapper.map(especialidadeRequest, Especialidade.class);
    especialidade = especialidadeRepository.save(especialidade);
    return modelMapper.map(especialidade, EspecialidadeResponseDTO.class); // Retornar o dto criado
  }

  public EspecialidadeResponseDTO atualizarEspecialidade(Long id, EspecialidadeRequestDTO especialidadeDto) {
    log.info("Atualizando especialidade com ID: {}", id);
    Especialidade especialidadeExistente = especialidadeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Especialidade não encontrada com ID: " + id));
    modelMapper.map(especialidadeDto, especialidadeExistente);
    Especialidade especialidadeAtualizada = especialidadeRepository.save(especialidadeExistente);
    return modelMapper.map(especialidadeAtualizada, EspecialidadeResponseDTO.class);
  }

  public void removerEspecialidade(Long id) {
    log.info("Removendo especialidade com ID: {}", id);
    Especialidade especialidade = especialidadeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Especialidade não encontrada com ID: " + id));
    especialidadeRepository.delete(especialidade);
  }

  public EspecialidadeResponseDTO buscarEspecialidadePorId(Long id) {
    log.info("Buscando especialidade com ID: {}", id);
    Especialidade especialidade = especialidadeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Especialidade não encontrada com ID: " + id));
    return modelMapper.map(especialidade, EspecialidadeResponseDTO.class);
  }

  public List<EspecialidadeResponseDTO> listarEspecialidades() {
    log.info("Listando todas as especialidades");
    List<Especialidade> especialidades = especialidadeRepository.findAll();
    return especialidades.stream()
        .map(especialidade -> modelMapper.map(especialidade, EspecialidadeResponseDTO.class))
        .toList();
  }
}
