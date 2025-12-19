package com.safebank.service;

import com.safebank.entity.Usuario;
import com.safebank.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Usamos MockitoExtension para inicializar los mocks automáticamente.
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void registrarUsuario_Exitoso() {
        // GIVEN: Un usuario nuevo válido
        Usuario nuevoUsuario = new Usuario("Juan", "juan@test.com", "1234", BigDecimal.ZERO);

        // Simulamos que el email NO existe
        when(usuarioRepository.findByEmail("juan@test.com")).thenReturn(Optional.empty());
        // Simulamos la encriptación
        when(passwordEncoder.encode("1234")).thenReturn("encoded_1234");
        // Simulamos el guardado (devuelve el mismo usuario)
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(nuevoUsuario);

        // WHEN: Llamamos al servicio
        Usuario resultado = usuarioService.registrarUsuario(nuevoUsuario);

        // THEN: Verificamos que se llamó al repo y el password está "encriptado" (en el
        // objeto pasado al save)
        assertNotNull(resultado);
        assertEquals("encoded_1234", resultado.getPassword());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void registrarUsuario_EmailDuplicado_LanzaExcepcion() {
        // GIVEN: Un usuario con email existente
        Usuario usuario = new Usuario("Juan", "juan@test.com", "1234", BigDecimal.ZERO);

        // Simulamos que el email YA existe
        when(usuarioRepository.findByEmail("juan@test.com")).thenReturn(Optional.of(new Usuario()));

        // WHEN & THEN: Esperamos una excepción
        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.registrarUsuario(usuario);
        });

        // Verificamos que NUNCA se intentó guardar
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}
