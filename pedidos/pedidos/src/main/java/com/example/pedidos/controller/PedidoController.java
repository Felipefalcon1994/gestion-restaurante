package com.example.pedidos.controller;


import com.example.pedidos.dto.PedidoDTO;
import com.example.pedidos.model.Pedido;
import com.example.pedidos.service.PedidoService;

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
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos",description ="Operaciones CRUD de Pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(summary ="Creación de pedido",description ="Genera pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody PedidoDTO dto) {
        return new ResponseEntity<>(pedidoService.crearPedido(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary ="Lista de pedidos",description ="Muestra todos los pedidos")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200",description = "Lista de Pedidos obtenida con exito"),
        @ApiResponse(responseCode = "500",description = "Error interno del Servidor")
    })
    public ResponseEntity<List<Pedido>> listarTodos() {
        return new ResponseEntity<>(pedidoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary="Pedidos por Id",description ="Busqueda de Pedidos por su Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> obtenerPorId(@Parameter(description = "Id del Pedido", required = true) @PathVariable Long id) {
        return new ResponseEntity<>(pedidoService.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary ="Pedidos por Estado",description ="Busqueda de Pedidos por su estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pedidos por estado"),
        @ApiResponse(responseCode = "400", description = "Estado inválido")
    })
    public ResponseEntity<List<Pedido>> listarPorEstado(@Parameter(description = "Estado del Pedido", required = true)@PathVariable String estado) {
        return new ResponseEntity<>(pedidoService.listarPorEstado(Pedido.EstadoPedido.valueOf(estado)), HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Pedidos por usuarios",description = "Busqueda de Pedidos por su usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedidos del usuario encontrados"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<List<Pedido>> listarPorUsuario(@Parameter(description = "Id del Usuario", required = true)@PathVariable Long usuarioId) {
        return new ResponseEntity<>(pedidoService.listarPorUsuario(usuarioId), HttpStatus.OK);
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado del Pedido",description = "Actualizacion del estado del pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Estado inválido"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> actualizarEstado(@Parameter(description = "Estado del Pedido",required = true)@PathVariable Long id, 
                                                   @Parameter(description = "Nuevo estado del Pedido", required = true) @RequestParam String estado) {
        return new ResponseEntity<>(pedidoService.actualizarEstado(id, estado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido",description = "Se elimina pedido por su Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Void> eliminarPedido(@Parameter(description = "Eliminacion de Pedido",required = true)@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
