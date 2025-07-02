package com.clinica.controllers;

import com.clinica.dto.ApiResponse;
import com.clinica.dtos.AtendimentoRequestDTO;
import com.clinica.dtos.AtendimentoResponseDTO;
import com.clinica.dtos.AtendimentoUpdateDTO;
import com.clinica.services.AtendimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/api/v1/atendimentos")
@Tag(name = "Atendimentos", description = "API para gerenciamento de atendimentos médicos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar novo atendimento", description = "Cria um novo registro de atendimento médico")
    public ResponseEntity<ApiResponse<EntityModel<AtendimentoResponseDTO>>> registrarAtendimento(
            @Valid @RequestBody AtendimentoRequestDTO dto) {
        log.info("Recebida requisição para registrar atendimento: {}", dto);
        
        AtendimentoResponseDTO novo = atendimentoService.registrar(dto);
        EntityModel<AtendimentoResponseDTO> entityModel = EntityModel.of(novo);
        
        // Adicionar links HATEOAS
        entityModel.add(linkTo(methodOn(AtendimentoController.class).buscarPorId(novo.getId())).withSelfRel());
        entityModel.add(linkTo(methodOn(AtendimentoController.class).listarPorPaciente(novo.getPacienteId())).withRel("atendimentos-paciente"));
        entityModel.add(linkTo(methodOn(AtendimentoController.class).listarPorMedico(novo.getMedicoId())).withRel("atendimentos-medico"));
        
        log.info("Atendimento registrado com ID: {}", novo.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(entityModel, "Atendimento registrado com sucesso"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar atendimento por ID", description = "Retorna um atendimento específico pelo seu ID")
    public ResponseEntity<ApiResponse<EntityModel<AtendimentoResponseDTO>>> buscarPorId(
            @Parameter(description = "ID do atendimento") @PathVariable Long id) {
        log.info("Buscando atendimento com ID: {}", id);
        
        AtendimentoResponseDTO atendimento = atendimentoService.buscarPorId(id);
        EntityModel<AtendimentoResponseDTO> entityModel = EntityModel.of(atendimento);
        
        // Adicionar links HATEOAS
        entityModel.add(linkTo(methodOn(AtendimentoController.class).buscarPorId(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(AtendimentoController.class).listarTodos()).withRel("todos-atendimentos"));
        
        return ResponseEntity.ok(ApiResponse.success(entityModel, "Atendimento encontrado"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todos os atendimentos", description = "Retorna todos os atendimentos registrados")
    public ResponseEntity<ApiResponse<List<AtendimentoResponseDTO>>> listarTodos() {
        log.info("Listando todos os atendimentos");
        
        List<AtendimentoResponseDTO> atendimentos = atendimentoService.listarTodos();
        
        return ResponseEntity.ok(ApiResponse.success(atendimentos, "Atendimentos listados com sucesso"));
    }

    @GetMapping("/ativos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar atendimentos ativos", description = "Retorna apenas os atendimentos ativos")
    public ResponseEntity<ApiResponse<List<AtendimentoResponseDTO>>> listarAtivos() {
        log.info("Listando atendimentos ativos");
        
        List<AtendimentoResponseDTO> atendimentos = atendimentoService.listarAtivos();
        
        return ResponseEntity.ok(ApiResponse.success(atendimentos, "Atendimentos ativos listados com sucesso"));
    }

    @GetMapping("/paciente/{pacienteId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar atendimentos por paciente", description = "Retorna todos os atendimentos de um paciente específico")
    public ResponseEntity<ApiResponse<List<AtendimentoResponseDTO>>> listarPorPaciente(
            @Parameter(description = "ID do paciente") @PathVariable Long pacienteId) {
        log.info("Buscando atendimentos do paciente ID: {}", pacienteId);
        
        List<AtendimentoResponseDTO> lista = atendimentoService.listarPorPaciente(pacienteId);
        
        log.info("Encontrados {} atendimentos para paciente ID: {}", lista.size(), pacienteId);
        return ResponseEntity.ok(ApiResponse.success(lista, "Atendimentos do paciente listados com sucesso"));
    }

    @GetMapping("/medico/{medicoId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar atendimentos por médico", description = "Retorna todos os atendimentos de um médico específico")
    public ResponseEntity<ApiResponse<List<AtendimentoResponseDTO>>> listarPorMedico(
            @Parameter(description = "ID do médico") @PathVariable Long medicoId) {
        log.info("Buscando atendimentos do médico ID: {}", medicoId);
        
        List<AtendimentoResponseDTO> lista = atendimentoService.listarPorMedico(medicoId);
        
        log.info("Encontrados {} atendimentos para médico ID: {}", lista.size(), medicoId);
        return ResponseEntity.ok(ApiResponse.success(lista, "Atendimentos do médico listados com sucesso"));
    }

    @GetMapping("/periodo")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar atendimentos por período", description = "Retorna atendimentos em um período específico")
    public ResponseEntity<ApiResponse<List<AtendimentoResponseDTO>>> buscarPorPeriodo(
            @Parameter(description = "Data e hora de início (formato: yyyy-MM-dd HH:mm:ss)")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime inicio,
            @Parameter(description = "Data e hora de fim (formato: yyyy-MM-dd HH:mm:ss)")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fim) {
        log.info("Buscando atendimentos entre {} e {}", inicio, fim);
        
        List<AtendimentoResponseDTO> atendimentos = atendimentoService.buscarPorPeriodo(inicio, fim);
        
        return ResponseEntity.ok(ApiResponse.success(atendimentos, "Atendimentos do período listados com sucesso"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar atendimento", description = "Atualiza os dados de um atendimento existente")
    public ResponseEntity<ApiResponse<EntityModel<AtendimentoResponseDTO>>> atualizarAtendimento(
            @Parameter(description = "ID do atendimento") @PathVariable Long id,
            @Valid @RequestBody AtendimentoUpdateDTO dto) {
        log.info("Recebida requisição para atualizar atendimento ID: {} com dados: {}", id, dto);
        
        AtendimentoResponseDTO atualizado = atendimentoService.atualizar(id, dto);
        EntityModel<AtendimentoResponseDTO> entityModel = EntityModel.of(atualizado);
        
        // Adicionar links HATEOAS
        entityModel.add(linkTo(methodOn(AtendimentoController.class).buscarPorId(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(AtendimentoController.class).listarTodos()).withRel("todos-atendimentos"));
        
        log.info("Atendimento ID: {} atualizado com sucesso", id);
        return ResponseEntity.ok(ApiResponse.success(entityModel, "Atendimento atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Cancelar atendimento", description = "Cancela um atendimento (soft delete)")
    public ResponseEntity<Void> cancelarAtendimento(
            @Parameter(description = "ID do atendimento") @PathVariable Long id) {
        log.info("Recebida requisição para cancelar atendimento ID: {}", id);
        
        atendimentoService.cancelar(id);
        
        log.info("Atendimento ID: {} cancelado com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}

