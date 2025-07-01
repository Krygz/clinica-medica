package com.clinica.controllers;

import com.clinica.services.ActionsApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/actions-application")
public class ActionsApplicationController {

    private final ActionsApplicationService actionsApplicationService;

    public ActionsApplicationController(ActionsApplicationService actionsApplicationService) {
        this.actionsApplicationService = actionsApplicationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> getActionsApplication() {
        log.info("Buscando ações da aplicação");
        return actionsApplicationService.getActionsApplication();
    }

}