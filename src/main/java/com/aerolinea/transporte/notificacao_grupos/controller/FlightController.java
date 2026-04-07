package com.aerolinea.transporte.notificacao_grupos.controller;

import com.aerolinea.transporte.notificacao_grupos.model.Flight;
import com.aerolinea.transporte.notificacao_grupos.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
@Tag(name = "Flights", description = "Endpoints for managing flights")
@CrossOrigin(origins = {"https://frontend-aeroline.vercel.app", "http://localhost:4200"})
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    @Operation(summary = "Create a new flight", description = "Creates a new flight and stores it locally")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @GetMapping
    @Operation(summary = "Get all flights", description = "Retrieves all stored flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get flight by ID", description = "Retrieves a specific flight by its ID")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flight);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a flight", description = "Updates an existing flight with new details")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(id, flightDetails);
        if (updatedFlight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a flight", description = "Deletes a flight by its ID")
    public ResponseEntity<Map<String, String>> deleteFlight(@PathVariable Long id) {
        boolean deleted = flightService.deleteFlight(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("message", "Flight deleted successfully"));
    }
}

