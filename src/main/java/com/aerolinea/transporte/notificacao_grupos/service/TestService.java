package com.aerolinea.transporte.notificacao_grupos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    public TestService() {
        logger.info("TestService inicializado");
    }

    /**
     * Retorna uma mensagem de teste simples
     * @return Map com mensagem de teste
     */
    public Map<String, String> getTestMessage() {
        logger.debug("Método getTestMessage chamado");
        Map<String, String> response = new HashMap<>();
        response.put("message", "API está funcionando!");
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.put("status", "OK");
        logger.info("Teste concluído com sucesso");
        return response;
    }

    /**
     * Valida um valor de teste
     * @param value Valor a ser validado
     * @return Resultado da validação
     */
    public Map<String, Object> validateTestValue(String value) {
        logger.debug("Validando valor: {}", value);
        Map<String, Object> response = new HashMap<>();

        if (value == null || value.trim().isEmpty()) {
            logger.warn("Valor de teste vazio ou nulo");
            response.put("isValid", false);
            response.put("message", "Valor não pode ser vazio");
            return response;
        }

        logger.info("Valor validado com sucesso: {}", value);
        response.put("isValid", true);
        response.put("message", "Valor é válido");
        response.put("value", value);
        response.put("length", value.length());
        return response;
    }

    /**
     * Obtém informações de teste da aplicação
     * @return Map com informações da aplicação
     */
    public Map<String, String> getApplicationInfo() {
        logger.debug("Obtendo informações da aplicação");
        Map<String, String> info = new HashMap<>();
        info.put("applicationName", "Notificacao Grupos");
        info.put("version", "0.0.1-SNAPSHOT");
        info.put("environment", System.getProperty("app.environment", "development"));
        info.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        info.put("javaVersion", System.getProperty("java.version"));
        logger.info("Informações da aplicação recuperadas");
        return info;
    }

    /**
     * Realiza um teste de echo
     * @param input Texto de entrada
     * @return Map com echo da entrada
     */
    public Map<String, String> echo(String input) {
        logger.debug("Echo chamado com input: {}", input);
        Map<String, String> response = new HashMap<>();
        response.put("input", input != null ? input : "");
        response.put("output", "Echo: " + (input != null ? input : ""));
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        logger.info("Echo processado com sucesso");
        return response;
    }
}

