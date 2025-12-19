package com.safebank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

// Esta clase representa a nuestros clientes en la base de datos.
// Usamos JPA para mapearla automáticamente a una tabla "usuarios".
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // El nombre completo del usuario. No puede estar vacío.
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    // El email sirve como identificador único para el login.
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    @Column(unique = true)
    private String email;

    // La contraseña se guardará encriptada (hash). NUNCA en texto plano.
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // El saldo actual de la cuenta. Usamos BigDecimal para evitar errores de
    // redondeo con dinero.
    // Empezamos con 0 por defecto y no permitimos negativos (aunque en un banco
    // real podría haber descubiertos).
    @Min(value = 0, message = "El saldo no puede ser negativo")
    private BigDecimal saldo;

    // Constructor vacío requerido por JPA
    public Usuario() {
    }

    // Constructor para facilitar la creación de usuarios
    public Usuario(String nombre, String email, String password, BigDecimal saldo) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
    }

    // Getters y Setters estándar
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
