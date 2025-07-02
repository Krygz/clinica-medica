package com.clinica.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActuatorConfig implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        details.put("servico", "Atendimento Médico");
        details.put("versao", "1.0.0");
        details.put("descricao", "Micro serviço responsável pelo gerenciamento de atendimentos médicos");
        details.put("tecnologias", "Spring Boot, JPA, MySQL, OpenAPI");
        
        builder.withDetail("aplicacao", details);
    }
} 