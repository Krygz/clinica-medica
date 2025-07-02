package com.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.clinica"})
@EnableJpaRepositories(basePackages = {"com.clinica.repositories"})
@EntityScan(basePackages = {"com.clinica.models"})
public class ClinicaMedicaAgendamentoApp {
    public static void main(String[] args) {
        SpringApplication.run(ClinicaMedicaAgendamentoApp.class, args);
    }
}