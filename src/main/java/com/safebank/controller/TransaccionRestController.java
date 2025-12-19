package com.safebank.controller;

import com.safebank.dto.OperacionRequest;
import com.safebank.dto.TransaccionDTO;
import com.safebank.dto.TransferenciaRequest;
import com.safebank.entity.Transaccion;
import com.safebank.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transacciones")
@Tag(name = "Transacciones", description = "Operaciones de transferencia y consulta de historial")
public class TransaccionRestController {

    private final TransaccionService transaccionService;

    public TransaccionRestController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping("/transferir")
    @Operation(summary = "Realizar transferencia", description = "Transfiere dinero de un usuario a otro.")
    public ResponseEntity<TransaccionDTO> transferir(@Valid @RequestBody TransferenciaRequest request) {
        Transaccion transaccion = transaccionService.realizarTransferencia(
                request.getEmailOrigen(),
                request.getEmailDestino(),
                request.getMonto());

        return ResponseEntity.ok(mapearADTO(transaccion));
    }

    @PostMapping("/depositar")
    @Operation(summary = "Realizar depósito", description = "Ingresa dinero en la cuenta del usuario.")
    public ResponseEntity<TransaccionDTO> depositar(@RequestParam String email,
            @Valid @RequestBody OperacionRequest request) {
        Transaccion transaccion = transaccionService.depositar(email, request.getMonto());
        return ResponseEntity.ok(mapearADTO(transaccion));
    }

    @PostMapping("/retirar")
    @Operation(summary = "Realizar retiro", description = "Retira dinero de la cuenta del usuario.")
    public ResponseEntity<TransaccionDTO> retirar(@RequestParam String email,
            @Valid @RequestBody OperacionRequest request) {
        Transaccion transaccion = transaccionService.retirar(email, request.getMonto());
        return ResponseEntity.ok(mapearADTO(transaccion));
    }

    @GetMapping("/historial")
    @Operation(summary = "Obtener historial", description = "Obtiene todas las transacciones de un usuario (origen o destino).")
    public ResponseEntity<List<TransaccionDTO>> obtenerHistorial(@RequestParam String email) {
        List<Transaccion> transacciones = transaccionService.obtenerHistorial(email);

        List<TransaccionDTO> dtos = transacciones.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // Método helper para convertir Entidad a DTO
    private TransaccionDTO mapearADTO(Transaccion t) {
        return new TransaccionDTO(
                t.getId(),
                t.getMonto(),
                t.getFecha(),
                t.getTipo(),
                t.getUsuarioOrigen() != null ? t.getUsuarioOrigen().getEmail() : "Cajero/Sistema",
                t.getUsuarioDestino() != null ? t.getUsuarioDestino().getEmail() : "Cajero/Sistema");
    }
}
