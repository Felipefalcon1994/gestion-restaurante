package com.example.pagos.controller;

import com.example.pagos.dto.PagoDTO;
import com.example.pagos.model.Pago;
import com.example.pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "Operaciones CRUD de Pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    @Operation(summary = "Procesar pago", description = "Genera un nuevo pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    public ResponseEntity<Pago> procesarPago(@Valid @RequestBody PagoDTO dto) {
        return new ResponseEntity<>(pagoService.procesarPago(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lista de pagos", description = "Muestra todos los pagos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Pago>> listarTodos() {
        return new ResponseEntity<>(pagoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pago por Id", description = "Búsqueda de pago por su Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Pago> obtenerPorId(
        @Parameter(description = "Id del pago", required = true) @PathVariable Long id) {
        return new ResponseEntity<>(pagoService.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/pedido/{pedidoId}")
    @Operation(summary = "Pago por pedido", description = "Búsqueda de pago por Id de pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Pago> obtenerPorPedido(
        @Parameter(description = "Id del pedido", required = true) @PathVariable Long pedidoId) {
        return new ResponseEntity<>(pagoService.obtenerPorPedido(pedidoId), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Pagos por estado", description = "Búsqueda de pagos por su estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos por estado"),
        @ApiResponse(responseCode = "400", description = "Estado inválido")
    })
    public ResponseEntity<List<Pago>> listarPorEstado(
        @Parameter(description = "Estado del pago", required = true) @PathVariable String estado) {
        return new ResponseEntity<>(pagoService.listarPorEstado(Pago.EstadoPago.valueOf(estado)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pago", description = "Se elimina pago por su Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pago eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Void> eliminarPago(
        @Parameter(description = "Id del pago a eliminar", required = true) @PathVariable Long id) {
        pagoService.eliminarPago(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
