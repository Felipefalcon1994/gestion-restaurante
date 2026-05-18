package com.example.pagos.service;

import java.time.LocalDateTime;

import com.example.pagos.client.PedidoClient;
import com.example.pagos.dto.PagoDTO;
import com.example.pagos.dto.PedidoDTO;
import com.example.pagos.model.Pago;
import com.example.pagos.repository.PagoRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PagoService {
    
    private final PagoRepository pagoRepository;
    private final PedidoClient pedidoClient;

    public PagoService(PagoRepository pagoRepository, PedidoClient pedidoClient) {
        this.pagoRepository = pagoRepository;
        this.pedidoClient = pedidoClient;
    }

    public Pago procesarPago(PagoDTO dto) {
        // Verificar que el pedido existe
        PedidoDTO pedido = pedidoClient.buscarPedidoPorId(dto.getPedidoIdExterno());
        if (pedido == null) {
            throw new RuntimeException("Pedido no encontrado con el ID: " + dto.getPedidoIdExterno());
        }

        // Verificar que no tenga ya un pago
        pagoRepository.findByPedidoIdExterno(dto.getPedidoIdExterno()).ifPresent(p -> {
            throw new RuntimeException("El pedido ya tiene un pago registrado");
        });

        Pago pago = new Pago();
        pago.setPedidoIdExterno(dto.getPedidoIdExterno());
        pago.setMonto(pedido.getTotal());
        pago.setMetodoPago(Pago.MetodoPago.valueOf(dto.getMetodoPago()));
        pago.setEstado(Pago.EstadoPago.COMPLETADO);
        pago.setFechaPago(LocalDateTime.now());

        // Actualizar estado del pedido a ENTREGADO
        pedidoClient.actualizarEstadoPedido(dto.getPedidoIdExterno(), "ENTREGADO");

        return pagoRepository.save(pago);
    }

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public Pago obtenerPorId(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con el ID: " + id));
    }

    public Pago obtenerPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoIdExterno(pedidoId)
                .orElseThrow(() -> new RuntimeException("No existe pago para el pedido ID: " + pedidoId));
    }

    public List<Pago> listarPorEstado(Pago.EstadoPago estado) {
        return pagoRepository.findByEstado(estado);
    }

    public void eliminarPago(Long id) {
        Pago pago = obtenerPorId(id);
        pagoRepository.delete(pago);
    }

}
