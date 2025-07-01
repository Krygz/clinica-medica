package com.clinica.controllers;

import com.clinica.dtos.PerfilRequestDTO;
import com.clinica.dtos.PerfilResponseDTO;
import com.clinica.services.PerfilService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/perfis")
public class PerfilController {
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerfilResponseDTO criarPerfil(@Valid @RequestBody PerfilRequestDTO perfilRequest) {
        log.info("Criando perfil - controller: {}", perfilRequest);
        return perfilService.adicionarPerfil(perfilRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PerfilResponseDTO atualizarPerfil(@PathVariable Long id, @Valid @RequestBody PerfilRequestDTO perfilDto) {
        log.info("Atualizar perfil com ID: {} - controller: {}", id, perfilDto);
        return perfilService.atualizarPerfil(id, perfilDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPerfil(@PathVariable Long id) {
        log.info("Remover perfil com ID: {} - controller", id);
        perfilService.removerPerfil(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PerfilResponseDTO buscarPerfilPorId(@PathVariable Long id) {
        log.info("Buscar perfil com ID: {} - controller", id);
        return perfilService.buscarPerfilPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<PerfilResponseDTO> listarPerfis() {
        log.info("Listar perfis - controller");
        return perfilService.listarPerfis();
    }
}