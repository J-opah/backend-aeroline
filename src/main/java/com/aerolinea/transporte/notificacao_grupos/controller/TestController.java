package com.aerolinea.transporte.notificacao_grupos.controller;

import com.aerolinea.transporte.notificacao_grupos.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test", description = "Endpoints para testes da API")
//@CrossOrigin(origins = {"http://localhost:1664", "http://localhost:4200"})
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
        logger.info("TestController inicializado");
    }

    /**
     * Endpoint simples de teste
     */
    @GetMapping("/hello")
    @Operation(summary = "Teste simples", description = "Retorna uma mensagem simples para verificar se a API está funcionando")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Map.class)))
    })
    public ResponseEntity<Map<String, String>> hello() {
        logger.debug("GET /api/test/hello chamado");
        Map<String, String> response = testService.getTestMessage();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para validar um valor
     */
    @PostMapping("/validate")
    @Operation(summary = "Validar valor", description = "Valida um valor de entrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validação concluída",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Map<String, Object>> validateValue(@RequestBody Map<String, String> payload) {
        logger.debug("POST /api/test/validate chamado com payload: {}", payload);

        if (payload == null || !payload.containsKey("value")) {
            logger.warn("Payload inválido - campo 'value' ausente");
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Campo 'value' é obrigatório"));
        }

        String value = payload.get("value");
        Map<String, Object> response = testService.validateTestValue(value);

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para obter informações da aplicação
     */
    @GetMapping("/info")
    @Operation(summary = "Informações da aplicação", description = "Retorna informações sobre a aplicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações obtidas com sucesso",
                    content = @Content(schema = @Schema(implementation = Map.class)))
    })
    public ResponseEntity<Map<String, String>> getInfo() {
        logger.debug("GET /api/test/info chamado");
        Map<String, String> info = testService.getApplicationInfo();
        return ResponseEntity.ok(info);
    }

    /**
     * Endpoint para teste de echo
     */
    @GetMapping("/echo")
    @Operation(summary = "Echo test", description = "Retorna o valor de entrada como eco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Echo retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = Map.class)))
    })
    public ResponseEntity<Map<String, String>> echo(
            @RequestParam(required = false, defaultValue = "Hello") String message) {
        logger.debug("GET /api/test/echo chamado com message: {}", message);
        Map<String, String> response = testService.echo(message);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint de healthcheck
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Verifica se a API está funcionando")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API está saudável")
    })
    public ResponseEntity<Map<String, String>> health() {
        logger.debug("GET /api/test/health chamado");
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "message", "API está funcionando normalmente"
        ));
    }
}

