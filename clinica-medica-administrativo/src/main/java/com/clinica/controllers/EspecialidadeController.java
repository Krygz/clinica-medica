package com.clinica.controllers;

import com.clinica.dtos.EspecialidadeRequestDTO;
import com.clinica.dtos.EspecialidadeResponseDTO;
import com.clinica.services.EspecialidadeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {
    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EspecialidadeResponseDTO criarEspecialidade(@Valid @RequestBody EspecialidadeRequestDTO especialidadeRequest) {
        log.info("Criando especialidade - controller: {}", especialidadeRequest);
        return especialidadeService.adicionarEspecialidade(especialidadeRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EspecialidadeResponseDTO atualizarEspecialidade(@PathVariable Long id, @Valid @RequestBody EspecialidadeRequestDTO especialidadeDto) {
        log.info("Atualizando especialidade com ID: {} - controller", id);
        return especialidadeService.atualizarEspecialidade(id, especialidadeDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEspecialidade(@PathVariable Long id) {
        log.info("Removendo especialidade com ID: {} - controller", id);
        especialidadeService.removerEspecialidade(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EspecialidadeResponseDTO buscarEspecialidadePorId(@PathVariable Long id) {
        log.info("Buscando especialidade com ID: {} - controller", id);
        return especialidadeService.buscarEspecialidadePorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<EspecialidadeResponseDTO> listarEspecialidades() {
        log.info("Listando todas as especialidades - controller");
        return especialidadeService.listarEspecialidades();
    }
}
