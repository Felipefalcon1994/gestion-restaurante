package com.example.pedidos.service;

import com.example.pedidos.client.MenuClient;
import com.example.pedidos.client.UsuarioClient;
import com.example.pedidos.dto.PedidoDTO;
import com.example.pedidos.dto.ProductoDTO;
import com.example.pedidos.dto.UsuarioDTO;
import com.example.pedidos.model.Pedido;
import com.example.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final MenuClient menuClient;
    private final UsuarioClient usuarioClient;

    public PedidoService(PedidoRepository pedidoRepository, MenuClient menuClient, UsuarioClient usuarioClient) {
        this.pedidoRepository = pedidoRepository;
        this.menuClient = menuClient;
        this.usuarioClient = usuarioClient;
    }

    public Pedido crearPedido(PedidoDTO dto) {
        // Verificar que el usuario existe
        UsuarioDTO usuario = usuarioClient.buscarUsuarioPorId(dto.getUsuarioIdExterno());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con el ID: " + dto.getUsuarioIdExterno());
        }

        // Verificar que el producto existe y está disponible
        ProductoDTO producto = menuClient.buscarProductoPorId(dto.getProductoIdExterno());
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado con el ID: " + dto.getProductoIdExterno());
        }
        if (!producto.isDisponible()) {
            throw new RuntimeException("El producto no está disponible: " + producto.getNombre());
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioIdExterno(dto.getUsuarioIdExterno());
        pedido.setProductoIdExterno(dto.getProductoIdExterno());
        pedido.setCantidad(dto.getCantidad());
        pedido.setTotal((double) producto.getPrecio() * dto.getCantidad());
        pedido.setEstado(Pedido.EstadoPedido.PENDIENTE);
        pedido.setFechaPedido(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id));
    }

    public List<Pedido> listarPorEstado(Pedido.EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioIdExterno(usuarioId);
    }

    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = obtenerPorId(id);
        pedido.setEstado(Pedido.EstadoPedido.valueOf(nuevoEstado));
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        Pedido pedido = obtenerPorId(id);
        pedidoRepository.delete(pedido);
    }
}
