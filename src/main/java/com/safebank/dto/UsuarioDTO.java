package com.safebank.dto;

import java.math.BigDecimal;

// DTO para devolver información del usuario al frontend/cliente.
// NO incluimos la contraseña aquí por seguridad.
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private BigDecimal saldo;

    public UsuarioDTO(Long id, String nombre, String email, BigDecimal saldo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.saldo = saldo;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
