package com.clinica.services;

import com.clinica.dtos.FuncionarioRequestDTO;
import com.clinica.dtos.FuncionarioResponseDTO;
import com.clinica.models.Funcionario;
import com.clinica.repositories.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FuncionarioService {

    private final ModelMapper modelMapper;
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(ModelMapper modelMapper, FuncionarioRepository funcionarioRepository) {
        this.modelMapper = modelMapper;
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioResponseDTO adicionarFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        log.info("Cadastro de funcionário - service: {}", funcionarioRequestDTO);
        Funcionario funcionario = modelMapper.map(funcionarioRequestDTO, Funcionario.class);
        funcionario = funcionarioRepository.save(funcionario);
        return modelMapper.map(funcionario, FuncionarioResponseDTO.class);
    }

    public FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioResponseDTO funcionarioResponseDTO) {
        log.info("Atualizando funcionário com ID: {}", id);
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        modelMapper.map(funcionarioResponseDTO, funcionarioExistente);
        Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionarioExistente);
        return modelMapper.map(funcionarioAtualizado, FuncionarioResponseDTO.class);
    }

    public void removerFuncionario(Long id) {
        log.info("Removendo funcionário com ID: {}", id);
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        funcionarioRepository.delete(funcionario);
    }

    public FuncionarioResponseDTO buscarFuncionarioPorId(Long id) {
        log.info("Buscando funcionário com ID: {}", id);
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        return modelMapper.map(funcionario, FuncionarioResponseDTO.class);
    }

    public List<FuncionarioResponseDTO> listarFuncionarios() {
        log.info("Listando todos os funcionários");
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(funcionario -> modelMapper.map(funcionario, FuncionarioResponseDTO.class))
                .toList();
    }

}
