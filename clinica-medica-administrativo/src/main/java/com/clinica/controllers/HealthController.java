package com.clinica.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> healthCheck() {
        log.info("Health check solicitado");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", "Clínica Médica - Administrativo");
        response.put("version", "1.0.0");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/ready")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> readinessCheck() {
        log.info("Readiness check solicitado");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "READY");
        response.put("timestamp", LocalDateTime.now());
        response.put("database", "CONNECTED");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/live")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> livenessCheck() {
        log.info("Liveness check solicitado");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ALIVE");
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }
} 