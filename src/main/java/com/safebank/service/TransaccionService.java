package com.safebank.service;

import com.safebank.entity.Transaccion;
import com.safebank.entity.Usuario;
import com.safebank.repository.TransaccionRepository;
import com.safebank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransaccionService {

    private final UsuarioRepository usuarioRepository;
    private final TransaccionRepository transaccionRepository;

    @Autowired
    public TransaccionService(UsuarioRepository usuarioRepository, TransaccionRepository transaccionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.transaccionRepository = transaccionRepository;
    }

    // Realiza una transferencia de dinero entre dos usuarios.
    // @Transactional asegura que si algo falla, se hace rollback de TODO (no se
    // pierde dinero).
    @Transactional
    public Transaccion realizarTransferencia(String emailOrigen, String emailDestino, BigDecimal monto) {
        // 1. Validaciones básicas
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (emailOrigen.equals(emailDestino)) {
            throw new IllegalArgumentException("No puedes transferirte dinero a ti mismo");
        }

        // 2. Buscar usuarios
        Usuario origen = usuarioRepository.findByEmail(emailOrigen)
                .orElseThrow(() -> new RuntimeException("Usuario origen no encontrado"));
        Usuario destino = usuarioRepository.findByEmail(emailDestino)
                .orElseThrow(() -> new RuntimeException("Usuario destino no encontrado"));

        // 3. Verificar saldo suficiente
        if (origen.getSaldo().compareTo(monto) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // 4. Actualizar saldos (La magia de la transacción)
        origen.setSaldo(origen.getSaldo().subtract(monto));
        destino.setSaldo(destino.getSaldo().add(monto));

        usuarioRepository.save(origen);
        usuarioRepository.save(destino);

        // 5. Registrar la transacción
        Transaccion transaccion = new Transaccion(monto, origen, destino, "TRANSFERENCIA");
        return transaccionRepository.save(transaccion);
    }

    // Obtiene el historial de transacciones de un usuario.
    public List<Transaccion> obtenerHistorial(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return transaccionRepository.findByUsuarioOrigenOrUsuarioDestinoOrderByFechaDesc(usuario, usuario);
    }
}
