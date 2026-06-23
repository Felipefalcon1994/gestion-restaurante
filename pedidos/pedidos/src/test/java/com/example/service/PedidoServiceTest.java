package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pedidos.client.MenuClient;
import com.example.pedidos.client.UsuarioClient;
import com.example.pedidos.model.Pedido;
import com.example.pedidos.repository.PedidoRepository;
import com.example.pedidos.service.PedidoService;


@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    
    @Mock
    private PedidoRepository pedidoRepository;

     @Mock
    private MenuClient menuClient;

    @Mock
    private UsuarioClient usuarioClient;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    @DisplayName("listarTodos debe retornar lista de pedidos")
        void listarTodos_retornaListaDePedidos() {
        // Arrange
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        when(pedidoRepository.findAll()).thenReturn(List.of(p1, p2));

        // Act
        List<Pedido> resultado = pedidoService.listarTodos();

        // Assert
        assertEquals(2, resultado.size());
        verify(pedidoRepository).findAll();
    }
    
    @Test
    @DisplayName("obtenerPorId debe retornar pedido existente")
    void obtenerPorId_retornaPedido() {
        // Arrange
        Pedido p1 = new Pedido();
        p1.setId(1L);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p1));

        // Act
        Pedido resultado = pedidoService.obtenerPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(pedidoRepository).findById(1L);
    }

    @Test
    @DisplayName("obtenerPorId debe lanzar excepción si no existe")
    void obtenerPorId_lanzaExcepcion() {
        // Arrange
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> pedidoService.obtenerPorId(99L));
        verify(pedidoRepository).findById(99L);
    }

    @Test
    @DisplayName("eliminarPedido debe eliminar pedido existente")
    void eliminarPedido_eliminaCorrectamente() {
        // Arrange
        Pedido p1 = new Pedido();
        p1.setId(1L);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p1));

        // Act
        pedidoService.eliminarPedido(1L);

        // Assert
        verify(pedidoRepository).delete(p1);
    }

    @Test
    @DisplayName("actualizarEstado debe cambiar el estado del pedido")
    void actualizarEstado_cambiaEstado() {
        // Arrange
        Pedido p1 = new Pedido();
        p1.setId(1L);
        p1.setEstado(Pedido.EstadoPedido.PENDIENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p1));
        when(pedidoRepository.save(p1)).thenReturn(p1);

        // Act
        Pedido resultado = pedidoService.actualizarEstado(1L, "EN_PROCESO");

        // Assert
        assertEquals(Pedido.EstadoPedido.EN_PROCESO, resultado.getEstado());
        verify(pedidoRepository).save(p1);
    }
}
