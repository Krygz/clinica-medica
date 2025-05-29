package com.clinica.services;

import org.springframework.stereotype.Service;

import com.clinica.dtos.ProntuarioRequestDTO;
import com.clinica.dtos.ProntuarioResponseDTO;
import com.clinica.models.Prontuario;
import com.clinica.repositories.ProntuarioRepository;

import java.util.List;

import org.modelmapper.ModelMapper;

@Service
public class ProntuarioService {

  private final ProntuarioRepository prontuarioRepository;
  private final ModelMapper modelMapper;

  public ProntuarioService(ProntuarioRepository prontuarioRepository, ModelMapper modelMapper) {
    this.prontuarioRepository = prontuarioRepository;
    this.modelMapper = modelMapper;
  }

  public ProntuarioResponseDTO CadastrarNovoProntuario(ProntuarioRequestDTO request) {
    Prontuario prontuario = modelMapper.map(request, Prontuario.class);
    prontuarioRepository.save(prontuario);
    return modelMapper.map(prontuario, ProntuarioResponseDTO.class);
  }

  public ProntuarioResponseDTO getProntuarioById(Long id) {
    Prontuario prontuario = prontuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("O id requerido não foi encontrado"));
    return modelMapper.map(prontuario, ProntuarioResponseDTO.class);
  }

  public ProntuarioResponseDTO atualizarPResponseDTOrontuario(Long id, ProntuarioRequestDTO request) {
    Prontuario prontuario = prontuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id not found"));

    modelMapper.map(request, Prontuario.class);
    prontuarioRepository.save(prontuario);
    return modelMapper.map(prontuario, ProntuarioResponseDTO.class);
  }

  public List<ProntuarioResponseDTO> getManyProntuario() {
    List<Prontuario> list = prontuarioRepository.findAll();
    return list.stream().map(
        prontuario -> modelMapper.map(list, ProntuarioResponseDTO.class)).toList();
  }

  public void deleteProntuario(Long id) {
    prontuarioRepository.deleteById(id);
  }
}
