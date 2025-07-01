package com.clinica.services;

import com.clinica.enums.Tipo;
import com.clinica.models.Funcionario;
import com.clinica.repositories.FuncionarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@Service
public class InicializadorService {

    private final FuncionarioRepository funcionarioRepository;

    public InicializadorService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostConstruct
    public void inicializarAdmin() {
        log.info("Inicializando usuário admin...");
        
        try {
            // Verificar se o admin já existe
            if (funcionarioRepository.findByUsuario("admin").isEmpty()) {
                // Criar usuário admin
                Funcionario admin = new Funcionario();
                admin.setUsuario("admin");
                admin.setSenha("admin");
                admin.setIdade(30);
                admin.setSexo('M');
                admin.setCpf("00000000000");
                admin.setRua("Rua Admin");
                admin.setNumero("1");
                admin.setComplemento("");
                admin.setBairro("Centro");
                admin.setCidade("Cidade");
                admin.setEstado("UF");
                admin.setContato("999999999");
                admin.setEmail("admin@clinica.com");
                admin.setDataNascimento(LocalDateTime.of(1990, 1, 1, 0, 0));
                admin.setEspecialidade(null);
                admin.setTipo(Tipo.ADMIN);
                
                funcionarioRepository.save(admin);
                log.info("Usuário admin criado com sucesso!");
            } else {
                log.info("Usuário admin já existe no banco de dados.");
            }
        } catch (Exception e) {
            log.error("Erro ao criar usuário admin: {}", e.getMessage());
        }
    }
} 