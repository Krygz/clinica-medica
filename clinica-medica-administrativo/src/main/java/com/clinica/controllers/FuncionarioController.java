package com.clinica.controllers;

import com.clinica.dtos.FuncionarioRequestDTO;
import com.clinica.dtos.FuncionarioResponseDTO;
import com.clinica.services.FuncionarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponseDTO criarFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequest) {
        log.info("Criando funcionário - controller: {}", funcionarioRequest);
        return funcionarioService.adicionarFuncionario(funcionarioRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioResponseDTO atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDTO funcionarioDto) {
        log.info("Atualizar funcionário - controller: {}", funcionarioDto);
        return funcionarioService.atualizarFuncionario(id, funcionarioDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFuncionario(@PathVariable Long id) {
        log.info("Remover funcionário - controller: {}", id);
        funcionarioService.removerFuncionario(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioResponseDTO buscarFuncionarioPorId(@PathVariable Long id) {
        log.info("Buscar funcionário - controller: {}", id);
        return funcionarioService.buscarFuncionarioPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<FuncionarioResponseDTO> listarFuncionarios() {
        log.info("Listar funcionários - controller");
        return funcionarioService.listarFuncionarios();
    }
}