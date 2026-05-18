package com.example.pedidos.controller;


import com.example.pedidos.dto.PedidoDTO;
import com.example.pedidos.model.Pedido;
import com.example.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody PedidoDTO dto) {
        return new ResponseEntity<>(pedidoService.crearPedido(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return new ResponseEntity<>(pedidoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(pedidoService.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> listarPorEstado(@PathVariable String estado) {
        return new ResponseEntity<>(pedidoService.listarPorEstado(Pedido.EstadoPedido.valueOf(estado)), HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> listarPorUsuario(@PathVariable Long usuarioId) {
        return new ResponseEntity<>(pedidoService.listarPorUsuario(usuarioId), HttpStatus.OK);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return new ResponseEntity<>(pedidoService.actualizarEstado(id, estado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
