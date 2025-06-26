package com.clinica.services;

import com.clinica.dtos.PerfilRequestDTO;
import com.clinica.dtos.PerfilResponseDTO;
import com.clinica.models.Perfil;
import com.clinica.repositories.PerfilRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PerfilService {
    private final ModelMapper modelMapper;
    private final PerfilRepository perfilRepository;

    public PerfilService(ModelMapper modelMapper, PerfilRepository perfilRepository) {
        this.modelMapper = modelMapper;
        this.perfilRepository = perfilRepository;
    }

    public PerfilResponseDTO adicionarPerfil(PerfilRequestDTO perfilRequest) {
        log.info("Cadastro de perfil - service: {}", perfilRequest);
        Perfil perfil = modelMapper.map(perfilRequest, Perfil.class);
        perfil = perfilRepository.save(perfil);
        return modelMapper.map(perfil, PerfilResponseDTO.class);
    }

    public PerfilResponseDTO atualizarPerfil(Long id, PerfilRequestDTO perfilDto) {
        log.info("Atualizando perfil com ID: {}", id);
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com ID: " + id));
        modelMapper.map(perfilDto, perfilExistente);
        Perfil perfilAtualizado = perfilRepository.save(perfilExistente);
        return modelMapper.map(perfilAtualizado, PerfilResponseDTO.class);
    }

    public void removerPerfil(Long id) {
        log.info("Removendo perfil com ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com ID: " + id));
        perfilRepository.delete(perfil);
    }

    public PerfilResponseDTO buscarPerfilPorId(Long id) {
        log.info("Buscando perfil com ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com ID: " + id));
        return modelMapper.map(perfil, PerfilResponseDTO.class);
    }

    public List<PerfilResponseDTO> listarPerfis() {
        log.info("Listando todos os perfis");
        List<Perfil> perfis = perfilRepository.findAll();
        return perfis.stream()
                .map(perfil -> modelMapper.map(perfil, PerfilResponseDTO.class))
                .toList();
    }
}