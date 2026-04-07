package com.aerolinea.transporte.notificacao_grupos.controller;

import com.aerolinea.transporte.notificacao_grupos.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Login", description = "Endpoints for user login")
//@CrossOrigin(origins = {"http://localhost:1664", "http://localhost:4200"})
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Validates user credentials and returns login status")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password are required"));
        }

        boolean isValid = loginService.validateLogin(username, password);
        if (isValid) {
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
    }
}
