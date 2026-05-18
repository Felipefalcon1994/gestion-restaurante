package com.example.pagos.controller;

import com.example.pagos.dto.PagoDTO;
import com.example.pagos.model.Pago;
import com.example.pagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> procesarPago(@Valid @RequestBody PagoDTO dto) {
        return new ResponseEntity<>(pagoService.procesarPago(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listarTodos() {
        return new ResponseEntity<>(pagoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(pagoService.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<Pago> obtenerPorPedido(@PathVariable Long pedidoId) {
        return new ResponseEntity<>(pagoService.obtenerPorPedido(pedidoId), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pago>> listarPorEstado(@PathVariable String estado) {
        return new ResponseEntity<>(pagoService.listarPorEstado(Pago.EstadoPago.valueOf(estado)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
