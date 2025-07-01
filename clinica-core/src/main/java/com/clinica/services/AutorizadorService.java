package com.clinica.services;

import com.clinica.exception.ActionClinicaMedicaException;
import com.clinica.models.Funcionario;
import com.clinica.models.Perfil;
import com.clinica.repositories.PerfilRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

@Slf4j
@Service
public class AutorizadorService {
    
    private final PerfilRepository perfilRepository;
    
    public AutorizadorService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }
    
    public void autorizar(Funcionario funcionario, String acao) {
        log.info("Verificando autorização para usuário: {} e ação: {}", funcionario.getUsuario(), acao);
        
        // Por enquanto, vamos permitir todas as ações para médicos e atendentes
        // Em uma implementação real, você verificaria o perfil do funcionário
        if (funcionario.getTipo() != null) {
            log.info("Autorização concedida para usuário: {} do tipo: {}", funcionario.getUsuario(), funcionario.getTipo());
            return;
        }
        
        log.warn("Usuário sem tipo definido, negando acesso: {}", funcionario.getUsuario());
        throw new ActionClinicaMedicaException("Usuário não tem permissão para esta ação");
    }
    
    public boolean verificarPermissao(Funcionario funcionario, String acao) {
        try {
            autorizar(funcionario, acao);
            return true;
        } catch (ActionClinicaMedicaException e) {
            return false;
        }
    }
}