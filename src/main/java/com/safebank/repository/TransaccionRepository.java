package com.safebank.repository;

import com.safebank.entity.Transaccion;
import com.safebank.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositorio para gestionar las transacciones.
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    // Queremos ver el historial de movimientos de un usuario.
    // Esto busca transacciones donde el usuario sea origen O destino.
    // Y las ordenamos por fecha descendente para ver las más recientes primero.
    List<Transaccion> findByUsuarioOrigenOrUsuarioDestinoOrderByFechaDesc(Usuario origen, Usuario destino);
}
