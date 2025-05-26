package com.clinica.services;

import org.springframework.stereotype.Service;

import com.clinica.dtos.ProntuarioRequestDTO;
import com.clinica.dtos.ProntuarioResponseDTO;
import com.clinica.models.Prontuario;
import com.clinica.repositories.ProntuarioRepository;

@Service
public class ProntuarioService {

  private final ProntuarioRepository prontuarioRepository;

  public ProntuarioService(ProntuarioRepository prontuarioRepository) {
    this.prontuarioRepository = prontuarioRepository;
  }

  public ProntuarioResponseDTO CadastrarNovoProntuario(ProntuarioRequestDTO request) {

  }

  public Prontuario getProntuarioById(Long id) {
    return prontuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("O id requerido não foi encontrado"));
  }

  public ProntuarioResponseDTO atualizarProntuario(Long id, ProntuarioRequestDTO request) {

  }

  public Prontuario getManyProntuario() {
    return null;
  }

  public void deleteProntuario() {
    return null;
  }

  private Prontuario toEntity(ProntuarioRequestDTO request) {
    Prontuario prontuario = new Prontuario();
    prontuario.setReceituario(request.receituario());
    prontuario.setExames(request.exames());
    prontuario.setObservacao(request.observacao());
    return prontuario;
  }
}
