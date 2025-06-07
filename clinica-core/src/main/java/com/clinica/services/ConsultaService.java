package com.clinica.services;

import com.clinica.models.Consulta;
import com.clinica.repositories.ConsultaRepository;

import java.util.List;
import java.util.Optional;

public class ConsultaService {private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta agendarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public void cancelarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

}
