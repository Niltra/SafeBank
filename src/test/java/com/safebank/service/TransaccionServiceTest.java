package com.safebank.service;

import com.safebank.entity.Transaccion;
import com.safebank.entity.Usuario;
import com.safebank.repository.TransaccionRepository;
import com.safebank.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransaccionServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionService transaccionService;

    @Test
    void realizarTransferencia_Exitoso() {
        // GIVEN: Dos usuarios, origen con saldo suficiente
        Usuario origen = new Usuario("Origen", "origen@test.com", "pass", new BigDecimal("1000"));
        Usuario destino = new Usuario("Destino", "destino@test.com", "pass", new BigDecimal("0"));
        BigDecimal monto = new BigDecimal("100");

        when(usuarioRepository.findByEmail("origen@test.com")).thenReturn(Optional.of(origen));
        when(usuarioRepository.findByEmail("destino@test.com")).thenReturn(Optional.of(destino));
        when(transaccionRepository.save(any(Transaccion.class))).thenAnswer(i -> i.getArguments()[0]);

        // WHEN: Realizamos la transferencia
        Transaccion resultado = transaccionService.realizarTransferencia("origen@test.com", "destino@test.com", monto);

        // THEN: Saldos actualizados y transacción creada
        assertEquals(new BigDecimal("900"), origen.getSaldo());
        assertEquals(new BigDecimal("100"), destino.getSaldo());
        assertNotNull(resultado);

        // Verificamos que se guardaron los cambios
        verify(usuarioRepository, times(2)).save(any(Usuario.class));
        verify(transaccionRepository).save(any(Transaccion.class));
    }

    @Test
    void realizarTransferencia_SaldoInsuficiente_LanzaExcepcion() {
        // GIVEN: Origen sin saldo suficiente
        Usuario origen = new Usuario("Origen", "origen@test.com", "pass", new BigDecimal("50"));
        Usuario destino = new Usuario("Destino", "destino@test.com", "pass", new BigDecimal("0"));
        BigDecimal monto = new BigDecimal("100");

        when(usuarioRepository.findByEmail("origen@test.com")).thenReturn(Optional.of(origen));
        when(usuarioRepository.findByEmail("destino@test.com")).thenReturn(Optional.of(destino));

        // WHEN & THEN: Esperamos excepción
        assertThrows(RuntimeException.class, () -> {
            transaccionService.realizarTransferencia("origen@test.com", "destino@test.com", monto);
        });

        // Verificamos que NO se actualizaron saldos ni se guardó transacción
        assertEquals(new BigDecimal("50"), origen.getSaldo()); // Saldo intacto
        verify(transaccionRepository, never()).save(any(Transaccion.class));
    }
}
