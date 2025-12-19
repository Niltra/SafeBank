package com.safebank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// DTO para mostrar transacciones en el historial.
public class TransaccionDTO {

    private Long id;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private String tipo;
    private String emailOrigen;
    private String emailDestino;

    public TransaccionDTO(Long id, BigDecimal monto, LocalDateTime fecha, String tipo, String emailOrigen,
            String emailDestino) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.tipo = tipo;
        this.emailOrigen = emailOrigen;
        this.emailDestino = emailDestino;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmailOrigen() {
        return emailOrigen;
    }

    public String getEmailDestino() {
        return emailDestino;
    }
}
