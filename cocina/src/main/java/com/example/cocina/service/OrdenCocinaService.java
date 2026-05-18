package com.example.cocina.service;

import com.example.cocina.client.PedidoClient;
import com.example.cocina.dto.OrdenCocinaDTO;
import com.example.cocina.dto.PedidoDTO;
import com.example.cocina.model.OrdenCocina;
import com.example.cocina.repository.OrdenCocinaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdenCocinaService {
    private final OrdenCocinaRepository ordenRepository;
    private final PedidoClient pedidoClient;

    public OrdenCocinaService(OrdenCocinaRepository ordenRepository, PedidoClient pedidoClient) {
        this.ordenRepository = ordenRepository;
        this.pedidoClient = pedidoClient;
    }

    public OrdenCocina crearOrden(OrdenCocinaDTO dto) {
        // Verificar que el pedido existe
        PedidoDTO pedido = pedidoClient.buscarPedidoPorId(dto.getPedidoIdExterno());
        if (pedido == null) {
            throw new RuntimeException("Pedido no encontrado con el ID: " + dto.getPedidoIdExterno());
        }

        OrdenCocina orden = new OrdenCocina();
        orden.setPedidoIdExterno(dto.getPedidoIdExterno());
        orden.setEstado(OrdenCocina.EstadoCocina.RECIBIDO);
        orden.setFechaRecepcion(LocalDateTime.now());
        orden.setObservaciones(dto.getObservaciones());

        // Actualizar estado del pedido a EN_COCINA
        pedidoClient.actualizarEstadoPedido(dto.getPedidoIdExterno(), "EN_COCINA");

        return ordenRepository.save(orden);
    }

    public List<OrdenCocina> listarTodas() {
        return ordenRepository.findAll();
    }

    public OrdenCocina obtenerPorId(Long id) {
        return ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de cocina no encontrada con el ID: " + id));
    }

    public List<OrdenCocina> listarPorEstado(OrdenCocina.EstadoCocina estado) {
        return ordenRepository.findByEstado(estado);
    }

    public OrdenCocina actualizarEstado(Long id, String nuevoEstado) {
        OrdenCocina orden = obtenerPorId(id);
        OrdenCocina.EstadoCocina estado = OrdenCocina.EstadoCocina.valueOf(nuevoEstado);
        orden.setEstado(estado);

        if (estado == OrdenCocina.EstadoCocina.LISTO) {
            orden.setFechaFinalizacion(LocalDateTime.now());
            // Actualizar estado del pedido a LISTO
            pedidoClient.actualizarEstadoPedido(orden.getPedidoIdExterno(), "LISTO");
        }

        return ordenRepository.save(orden);
    }

    public void eliminarOrden(Long id) {
        OrdenCocina orden = obtenerPorId(id);
        ordenRepository.delete(orden);
    }
}
