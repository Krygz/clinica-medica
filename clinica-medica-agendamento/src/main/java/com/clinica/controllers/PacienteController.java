package com.clinica.controllers;

import com.clinica.dtos.PacienteRequestDTO;
import com.clinica.dtos.PacienteResponseDTO;
import com.clinica.services.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/agendamento/pacientes")
public class PacienteController {

    private static final Logger log = LoggerFactory.getLogger(PacienteController.class);

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponseDTO criarPaciente(@RequestBody PacienteRequestDTO pacienteDTO) {
        log.info("Cadastro rápido para agendamento: {}", pacienteDTO.getNome());
        return pacienteService.cadastrarPaciente(pacienteDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteResponseDTO buscarPacientePorId(@PathVariable Long id) {
        log.info("Recebida requisição para buscar paciente por ID: {}", id);
        PacienteResponseDTO pacienteEncontrado = pacienteService.buscarPorId(id);
        log.info("Paciente ID: {} encontrado.", id);
        return pacienteEncontrado;
    }






















}
