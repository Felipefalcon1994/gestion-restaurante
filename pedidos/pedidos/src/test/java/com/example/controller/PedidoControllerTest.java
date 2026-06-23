package com.example.controller;

import com.example.pedidos.controller.PedidoController;
import com.example.pedidos.model.Pedido;
import com.example.pedidos.service.PedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    @DisplayName("GET /api/pedidos debe retornar lista de pedidos")
    void listarTodos_retorna200() throws Exception {
        Pedido p1 = new Pedido();
        p1.setId(1L);
        when(pedidoService.listarTodos()).thenReturn(List.of(p1));

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    @DisplayName("GET /api/pedidos/{id} debe retornar pedido por id")
    void obtenerPorId_retorna200() throws Exception {
        Pedido p1 = new Pedido();
        p1.setId(1L);
        when(pedidoService.obtenerPorId(1L)).thenReturn(p1);

        mockMvc.perform(get("/api/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("DELETE /api/pedidos/{id} debe retornar 204")
    void eliminarPedido_retorna204() throws Exception {
        mockMvc.perform(delete("/api/pedidos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("PATCH /api/pedidos/{id}/estado debe retornar pedido actualizado")
    void actualizarEstado_retorna200() throws Exception {
        Pedido p1 = new Pedido();
        p1.setId(1L);
        p1.setEstado(Pedido.EstadoPedido.EN_PROCESO);
        when(pedidoService.actualizarEstado(1L, "EN_PROCESO")).thenReturn(p1);

        mockMvc.perform(patch("/api/pedidos/1/estado")
                .param("estado", "EN_PROCESO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EN_PROCESO"));
    }
}