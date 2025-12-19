package com.safebank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// Cada movimiento de dinero queda registrado aquí.
// Es vital para la auditoría y para mostrar el historial al usuario.
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cuánto dinero se movió.
    @NotNull
    private BigDecimal monto;

    // Cuándo ocurrió. Se guarda automáticamente el momento de creación.
    private LocalDateTime fecha;

    // Quién envió el dinero. Puede ser null si es un depósito del sistema.
    @ManyToOne
    @JoinColumn(name = "usuario_origen_id")
    private Usuario usuarioOrigen;

    // Quién recibió el dinero.
    @ManyToOne
    @JoinColumn(name = "usuario_destino_id")
    private Usuario usuarioDestino;

    // Tipo de transacción para filtrar fácilmente (TRANSFERENCIA, DEPOSITO,
    // RETIRO).
    // Lo guardamos como String por simplicidad, podría ser un Enum.
    private String tipo;

    public Transaccion() {
    }

    public Transaccion(BigDecimal monto, Usuario usuarioOrigen, Usuario usuarioDestino, String tipo) {
        this.monto = monto;
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now(); // La fecha se pone sola al crear el objeto
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public void setUsuarioOrigen(Usuario usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
