package com.aerolinea.transporte.notificacao_grupos.model;

import java.util.Objects;

public class Flight {
    private Long id;
    private String seat;
    private String num;
    private Long entrada;
    private Long saida;
    private String orig;
    private String dest;
    private FlightStatus status;

    public enum FlightStatus {
        ATIVO("Ativo"),
        INATIVO("Inativo"),
        PENDENTE("Pendente");

        private final String value;

        FlightStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Flight() {
    }

    public Flight(Long id, String seat, String num, Long entrada, Long saida, String orig, String dest, FlightStatus status) {
        this.id = id;
        this.seat = seat;
        this.num = num;
        this.entrada = entrada;
        this.saida = saida;
        this.orig = orig;
        this.dest = dest;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Long getEntrada() {
        return entrada;
    }

    public void setEntrada(Long entrada) {
        this.entrada = entrada;
    }

    public Long getSaida() {
        return saida;
    }

    public void setSaida(Long saida) {
        this.saida = saida;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", seat='" + seat + '\'' +
                ", num='" + num + '\'' +
                ", entrada=" + entrada +
                ", saida=" + saida +
                ", orig='" + orig + '\'' +
                ", dest='" + dest + '\'' +
                ", status=" + status +
                '}';
    }
}

