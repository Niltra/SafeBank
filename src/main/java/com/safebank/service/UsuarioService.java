package com.safebank.service;

import com.safebank.entity.Usuario;
import com.safebank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyección por constructor (Best Practice).
    // Facilita el testing al no depender del contenedor de Spring para inyectar los
    // mocks.
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registra un nuevo usuario en el sistema.
    public Usuario registrarUsuario(Usuario usuario) {
        // 1. Validar que el email no exista ya.
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // 2. Encriptar la contraseña antes de guardarla.
        // NUNCA guardar contraseñas en texto plano.
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // 3. Guardar en base de datos.
        return usuarioRepository.save(usuario);
    }

    // Busca un usuario por su email.
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));
    }
}
