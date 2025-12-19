package com.safebank.repository;

import com.safebank.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Interfaz mágica de Spring Data JPA.
// Al extender JpaRepository, ya tenemos métodos como save(), findAll(), findById() sin escribir SQL.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Necesitamos buscar usuarios por email para el login y para transferencias.
    // Spring implementa esto automáticamente basándose en el nombre del método.
    Optional<Usuario> findByEmail(String email);

    // También podría ser útil verificar si existe un email antes de registrar
    boolean existsByEmail(String email);
}
