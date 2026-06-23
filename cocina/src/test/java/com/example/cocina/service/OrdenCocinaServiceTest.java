package com.example.cocina.service;

import com.example.cocina.client.PedidoClient;
import com.example.cocina.dto.OrdenCocinaDTO;
import com.example.cocina.dto.PedidoDTO;
import com.example.cocina.model.OrdenCocina;
import com.example.cocina.repository.OrdenCocinaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdenCocinaServiceTest {

    @Mock
    private OrdenCocinaRepository ordenRepository;

    @Mock
    private PedidoClient pedidoClient;

    @InjectMocks
    private OrdenCocinaService ordenService;

    @Test
    @DisplayName("crearOrden: debe guardar la orden con estado RECIBIDO y notificar a Pedidos")
    void crearOrden_debeGuardarOrdenYNotificarPedidos() {
        // Arrange
        OrdenCocinaDTO dto = new OrdenCocinaDTO();
        dto.setPedidoIdExterno(10L);
        dto.setObservaciones("Sin cebolla");

        OrdenCocina ordenGuardada = new OrdenCocina();
        ordenGuardada.setIdOrden(1L);
        ordenGuardada.setPedidoIdExterno(10L);
        ordenGuardada.setEstado(OrdenCocina.EstadoCocina.RECIBIDO);

        when(pedidoClient.buscarPedidoPorId(10L)).thenReturn(new PedidoDTO());
        when(ordenRepository.save(any(OrdenCocina.class))).thenReturn(ordenGuardada);

        // Act
        OrdenCocina resultado = ordenService.crearOrden(dto);

        // Assert
        assertThat(resultado.getEstado()).isEqualTo(OrdenCocina.EstadoCocina.RECIBIDO);
        assertThat(resultado.getPedidoIdExterno()).isEqualTo(10L);
        verify(ordenRepository).save(any(OrdenCocina.class));
        verify(pedidoClient).actualizarEstadoPedido(10L, "EN_COCINA");
    }

    @Test
    @DisplayName("crearOrden: debe lanzar excepción cuando el pedido externo no existe")
    void crearOrden_debeLanzarExcepcionSiPedidoNoExiste() {
        // Arrange
        OrdenCocinaDTO dto = new OrdenCocinaDTO();
        dto.setPedidoIdExterno(99L);

        when(pedidoClient.buscarPedidoPorId(99L)).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(() -> ordenService.crearOrden(dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");

        verify(ordenRepository, never()).save(any());
        verify(pedidoClient, never()).actualizarEstadoPedido(any(), any());
    }

    @Test
    @DisplayName("actualizarEstado: debe marcar la orden como LISTO, registrar fecha y notificar a Pedidos")
    void actualizarEstado_debeMarcarListoYNotificarPedidos() {
        // Arrange
        OrdenCocina orden = new OrdenCocina();
        orden.setIdOrden(1L);
        orden.setPedidoIdExterno(10L);
        orden.setEstado(OrdenCocina.EstadoCocina.EN_PREPARACION);

        when(ordenRepository.findById(1L)).thenReturn(Optional.of(orden));
        when(ordenRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // Act
        OrdenCocina resultado = ordenService.actualizarEstado(1L, "LISTO");

        // Assert
        assertThat(resultado.getEstado()).isEqualTo(OrdenCocina.EstadoCocina.LISTO);
        assertThat(resultado.getFechaFinalizacion()).isNotNull();
        verify(pedidoClient).actualizarEstadoPedido(eq(10L), eq("LISTO"));
    }

    @Test
    @DisplayName("actualizarEstado: debe lanzar excepción cuando la orden no existe (caso crítico)")
    void actualizarEstado_debeLanzarExcepcionSiOrdenNoExiste() {
        // Arrange
        when(ordenRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> ordenService.actualizarEstado(99L, "LISTO"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");

        verify(ordenRepository, never()).save(any());
        verify(pedidoClient, never()).actualizarEstadoPedido(any(), any());
    }
}
