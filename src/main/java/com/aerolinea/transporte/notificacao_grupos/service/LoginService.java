package com.aerolinea.transporte.notificacao_grupos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // In-memory user storage for simplicity (in production, use database)
    private final Map<String, String> users = new HashMap<>();

    public LoginService() {
        // Initialize with a default user
        String hashedPassword = passwordEncoder.encode("password123");
        users.put("admin", hashedPassword);
        logger.info("Default user 'admin' initialized with hashed password.");
    }

    public boolean validateLogin(String username, String password) {
        logger.info("Attempting login for user: {}", username);
        String storedHash = users.get(username);
        if (storedHash != null && passwordEncoder.matches(password, storedHash)) {
            logger.info("Login successful for user: {}", username);
            return true;
        } else {
            logger.warn("Login failed for user: {}", username);
            return false;
        }
    }

    public void registerUser(String username, String password) {
        logger.info("Registering new user: {}", username);
        String hashedPassword = passwordEncoder.encode(password);
        users.put(username, hashedPassword);
        logger.info("User {} registered successfully.", username);
    }
}
