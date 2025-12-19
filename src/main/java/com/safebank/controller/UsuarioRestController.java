package com.safebank.controller;

import com.safebank.dto.RegistroRequest;
import com.safebank.dto.UsuarioDTO;
import com.safebank.entity.Usuario;
import com.safebank.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios (Registro, Perfil)")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario", description = "Crea una nueva cuenta de usuario con saldo inicial 0.")
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody RegistroRequest request) {
        Usuario nuevoUsuario = new Usuario(
                request.getNombre(),
                request.getEmail(),
                request.getPassword(),
                BigDecimal.ZERO);

        Usuario usuarioGuardado = usuarioService.registrarUsuario(nuevoUsuario);

        UsuarioDTO response = new UsuarioDTO(
                usuarioGuardado.getId(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getSaldo());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
