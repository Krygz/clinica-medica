package com.clinica.services;

import com.clinica.dtos.ConvenioRequestDTO;
import com.clinica.dtos.ConvenioResponseDTO;
import com.clinica.models.Convenio;
import com.clinica.repositories.ConvenioRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConvenioService {

    private static final Logger log = LoggerFactory.getLogger(ConvenioService.class);

    private final ModelMapper modelMapper;
    private final ConvenioRepository convenioRepository;

    public ConvenioService(ModelMapper modelMapper, ConvenioRepository convenioRepository) {
        this.modelMapper = modelMapper;
        this.convenioRepository = convenioRepository;
    }

    public ConvenioResponseDTO adicionarConvenio(ConvenioRequestDTO convenioRequestDTO) {
        log.info("Cadastro de convênio - service: {}", convenioRequestDTO);
        Convenio convenio = modelMapper.map(convenioRequestDTO, Convenio.class);
        convenio = convenioRepository.save(convenio);
        return modelMapper.map(convenio, ConvenioResponseDTO.class);
    }

    public ConvenioResponseDTO atualizarConvenio(Integer id, ConvenioRequestDTO convenioRequestDTO) {
        log.info("Atualizando convênio com ID: {}", id);
        Convenio convenioExistente = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convênio não encontrado com ID: " + id));

        modelMapper.map(convenioRequestDTO, convenioExistente);
        Convenio convenioAtualizado = convenioRepository.save(convenioExistente);
        return modelMapper.map(convenioAtualizado, ConvenioResponseDTO.class);
    }

    public void removerConvenio(Integer id) {
        log.info("Removendo convênio com ID: {}", id);
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convênio não encontrado com ID: " + id));
        convenioRepository.delete(convenio);
    }

    public ConvenioResponseDTO buscarConvenioPorId(Integer id) {
        log.info("Buscando convênio com ID: {}", id);
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convênio não encontrado com ID: " + id));
        return modelMapper.map(convenio, ConvenioResponseDTO.class);
    }

    public List<ConvenioResponseDTO> listarConvenios() {
        log.info("Listando todos os convênios");
        List<Convenio> convenios = convenioRepository.findAll();
        return convenios.stream()
                .map(convenio -> modelMapper.map(convenio, ConvenioResponseDTO.class))
                .toList();
    }

}
