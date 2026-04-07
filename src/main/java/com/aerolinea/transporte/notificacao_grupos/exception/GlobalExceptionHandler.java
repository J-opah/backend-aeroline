package com.aerolinea.transporte.notificacao_grupos.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception e) {
        return ResponseEntity.status(500).body(Map.of("error", "Internal server error: " + e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", "Bad request: " + e.getMessage()));
    }

    // Add more specific handlers as needed
}
