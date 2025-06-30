package com.clinica.controllers;

import com.clinica.core.dto.AtendimentoRequestDTO;
import com.clinica.core.dto.AtendimentoResponseDTO;
import com.clinica.core.service.AtendimentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/atendimento/consultas")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtendimentoResponseDTO registrarAtendimento(@RequestBody AtendimentoRequestDTO dto) {
        log.info("Recebida requisição para registrar atendimento: {}", dto);
        AtendimentoResponseDTO novo = atendimentoService.registrar(dto);
        log.info("Atendimento registrado com ID: {}", novo.getId());
        return novo;
    }

    @GetMapping("/paciente/{pacienteId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AtendimentoResponseDTO> listarPorPaciente(@PathVariable Long pacienteId) {
        log.info("Buscando atendimentos do paciente ID: {}", pacienteId);
        List<AtendimentoResponseDTO> lista = atendimentoService.listarPorPaciente(pacienteId);
        log.info("Encontrados {} atendimentos para paciente ID: {}", lista.size(), pacienteId);
        return lista;
    }

    @DeleteMapping("/{atendimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarAtendimento(@PathVariable Long atendimentoId) {
        log.info("Recebida requisição para cancelar atendimento ID: {}", atendimentoId);
        atendimentoService.cancelar(atendimentoId);
        log.info("Atendimento ID: {} cancelado com sucesso", atendimentoId);
    }
}

