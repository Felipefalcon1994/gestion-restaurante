package com.example.cocina.controller;

import com.example.cocina.dto.OrdenCocinaDTO;
import com.example.cocina.model.OrdenCocina;
import com.example.cocina.service.OrdenCocinaService;
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

@Tag(name = "Cocina", description = "Gestión de órdenes de cocina y estados de preparación")
@RestController
@RequestMapping("/api/cocina")
public class OrdenCocinaController {
    private final OrdenCocinaService ordenService;

    public OrdenCocinaController(OrdenCocinaService ordenService) {
        this.ordenService = ordenService;
    }

    @Operation(
        summary = "Crear una nueva orden de cocina",
        description = "Recibe un ticket desde el microservicio de Pedidos y lo registra en la cola de cocina con estado RECIBIDO"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Orden creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<OrdenCocina> crearOrden(@Valid @RequestBody OrdenCocinaDTO dto) {
        return new ResponseEntity<>(ordenService.crearOrden(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas las órdenes de cocina")
    @ApiResponse(responseCode = "200", description = "Lista de órdenes devuelta correctamente")
    @GetMapping
    public ResponseEntity<List<OrdenCocina>> listarTodas() {
        return new ResponseEntity<>(ordenService.listarTodas(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener una orden por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Orden encontrada"),
        @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCocina> obtenerPorId(
            @Parameter(description = "ID de la orden de cocina", example = "1") @PathVariable Long id) {
        return new ResponseEntity<>(ordenService.obtenerPorId(id), HttpStatus.OK);
    }

    @Operation(
        summary = "Listar órdenes por estado",
        description = "Filtra las órdenes según su estado de preparación. Valores permitidos: RECIBIDO, EN_PREPARACION, LISTO"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista filtrada devuelta correctamente"),
        @ApiResponse(responseCode = "400", description = "Estado no válido")
    })
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenCocina>> listarPorEstado(
            @Parameter(description = "Estado de la orden", example = "RECIBIDO") @PathVariable String estado) {
        return new ResponseEntity<>(ordenService.listarPorEstado(OrdenCocina.EstadoCocina.valueOf(estado)), HttpStatus.OK);
    }

    @Operation(
        summary = "Actualizar el estado de una orden",
        description = "Cambia el estado de preparación de una orden. Flujo: RECIBIDO → EN_PREPARACION → LISTO"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Estado inválido"),
        @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenCocina> actualizarEstado(
            @Parameter(description = "ID de la orden", example = "1") @PathVariable Long id,
            @Parameter(description = "Nuevo estado de la orden", example = "EN_PREPARACION") @RequestParam String estado) {
        return new ResponseEntity<>(ordenService.actualizarEstado(id, estado), HttpStatus.OK);
    }

    @Operation(
        summary = "Eliminar una orden de cocina",
        description = "Elimina el ticket de cocina, por ejemplo cuando el pedido ha sido cancelado"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Orden eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(
            @Parameter(description = "ID de la orden a eliminar", example = "1") @PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
