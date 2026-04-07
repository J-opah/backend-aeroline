package com.aerolinea.transporte.notificacao_grupos.service;

import com.aerolinea.transporte.notificacao_grupos.model.Flight;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Service
public class FlightService {

    private final Map<Long, Flight> flightMap = new HashMap<>();
    private Long nextId = 1L;

    @PostConstruct
    public void initializeFlights() {
        Flight flight1 = new Flight(1L, "12A", "AV-001", 10L, 12L, "GRU - São Paulo", "CDG - Paris", Flight.FlightStatus.ATIVO);
        Flight flight2 = new Flight(2L, "14B", "AV-002", 11L, 13L, "GIG - Rio de Janeiro", "JFK - Nova York", Flight.FlightStatus.ATIVO);
        Flight flight3 = new Flight(3L, "3C", "AV-003", 10L, 12L, "BSB - Brasília", "LHR - Londres", Flight.FlightStatus.PENDENTE);
        Flight flight4 = new Flight(4L, "7D", "AV-004", 8L, 10L, "SSA - Salvador", "LIS - Lisboa", Flight.FlightStatus.INATIVO);
        Flight flight5 = new Flight(5L, "15E", "AV-005", 8L, 21L, "SSA - Salvador", "LIS - Lisboa", Flight.FlightStatus.INATIVO);
        Flight flight6 = new Flight(6L, "7B", "AV-006", 8L, 21L, "SSA - Salvador", "LIS - Lisboa", Flight.FlightStatus.INATIVO);
        Flight flight7 = new Flight(7L, "7A", "AV-007", 8L, 10L, "SSA - Salvador", "LIS - Lisboa", Flight.FlightStatus.INATIVO);

        flightMap.put(flight1.getId(), flight1);
        flightMap.put(flight2.getId(), flight2);
        flightMap.put(flight3.getId(), flight3);
        flightMap.put(flight4.getId(), flight4);
        flightMap.put(flight5.getId(), flight5);
        flightMap.put(flight6.getId(), flight6);
        flightMap.put(flight7.getId(), flight7);

        nextId = (long) flightMap.size()+1;
    }

    public Flight createFlight(Flight flight) {
        if (flight.getId() == null) {
            flight.setId(nextId++);
        }

        flightMap.put(flight.getId(), flight);
        return flight;
    }

    public List<Flight> getAllFlights() {
        return new ArrayList<>(flightMap.values());
    }

    public Flight getFlightById(Long id) {
        return flightMap.get(id);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        if (!flightMap.containsKey(id)) {
            return null;
        }
        Flight flight = flightMap.get(id);

        if (flightDetails.getSeat() != null) {
            flight.setSeat(flightDetails.getSeat());
        }
        if (flightDetails.getNum() != null) {
            flight.setNum(flightDetails.getNum());
        }
        if (flightDetails.getEntrada() != null) {
            flight.setEntrada(flightDetails.getEntrada());
        }
        if (flightDetails.getSaida() != null) {
            flight.setSaida(flightDetails.getSaida());
        }
        if (flightDetails.getOrig() != null) {
            flight.setOrig(flightDetails.getOrig());
        }
        if (flightDetails.getDest() != null) {
            flight.setDest(flightDetails.getDest());
        }
        if (flightDetails.getStatus() != null) {
            flight.setStatus(flightDetails.getStatus());
        }

        return flight;
    }

    public boolean deleteFlight(Long id) {
        return flightMap.remove(id) != null;
    }
}
