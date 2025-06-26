package com.clinica.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PerfilDto criarPerfil(@RequestBody PerfilRequest perfilRequest) {
        log.info("Criando perfil - controller: {}", perfilRequest);
        return perfilService.adicionarPerfil(perfilRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PerfilDto atualizarPerfil(@PathVariable Long id, @RequestBody PerfilDto perfilDto) {
        log.info("Atualizar perfil - controller: {}", perfilDto);
        return perfilService.atualizarPerfil(id, perfilDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPerfil(@PathVariable Long id) {
        log.info("Remover perfil - controller: {}", id);
        perfilService.removerPerfil(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PerfilDto buscarPerfilPorId(@PathVariable Long id) {
        log.info("Buscar perfil - controller: {}", id);
        return perfilService.buscarPerfilPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<PerfilDto> listarPerfis() {
        log.info("Listar perfis - controller");
        return perfilService.listarPerfis();
    }
}