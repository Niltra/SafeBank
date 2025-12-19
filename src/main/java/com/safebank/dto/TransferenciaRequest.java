package com.safebank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

// DTO para solicitar una transferencia.
public class TransferenciaRequest {

    @NotBlank(message = "El email de origen es obligatorio")
    @Email
    private String emailOrigen; // Temporalmente lo pedimos, luego lo sacaremos del token de seguridad

    @NotBlank(message = "El email de destino es obligatorio")
    @Email
    private String emailDestino;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    // Getters y Setters
    public String getEmailOrigen() {
        return emailOrigen;
    }

    public void setEmailOrigen(String emailOrigen) {
        this.emailOrigen = emailOrigen;
    }

    public String getEmailDestino() {
        return emailDestino;
    }

    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
