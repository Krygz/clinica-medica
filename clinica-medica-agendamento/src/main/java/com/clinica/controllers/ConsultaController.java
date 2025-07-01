package com.clinica.controllers;

import com.clinica.dtos.ConsultaRequestDTO;
import com.clinica.dtos.ConsultaResponseDTO;
import com.clinica.services.ConsultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/agendamento/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaResponseDTO agendarConsulta(@RequestBody ConsultaRequestDTO consultaDTO) {
        log.info("Recebida requisição para agendar nova consulta: {}", consultaDTO);
        ConsultaResponseDTO novaConsulta = consultaService.agendar(consultaDTO);
        log.info("Consulta agendada com sucesso com o ID: {}", novaConsulta.id());
        return novaConsulta;
    }

    @GetMapping("/paciente/{pacienteId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultaResponseDTO> buscarConsultasPorPaciente(@PathVariable Long pacienteId) {
        log.info("Recebida requisição para buscar consultas do paciente ID: {}", pacienteId);
        List<ConsultaResponseDTO> consultas = consultaService.listarPorPaciente(pacienteId);
        log.info("Encontradas {} consultas para o paciente ID: {}", consultas.size(), pacienteId);
        return consultas;
    }

    @DeleteMapping("/{consultaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarConsulta(@PathVariable Long consultaId) {
        log.info("Recebida requisição para cancelar a consulta ID: {}", consultaId);
        consultaService.cancelar(consultaId);
        log.info("Consulta ID: {} processada para cancelamento com sucesso.", consultaId);
    }
}
