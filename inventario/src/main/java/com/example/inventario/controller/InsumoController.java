package com.example.inventario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventario.dto.InsumoDTO;
import com.example.inventario.model.Insumo;
import com.example.inventario.service.InsumoService;


@Tag(name = "Insumos", description = "Gestión del inventario de insumos del restaurante")
@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    @Operation(summary = "Crear un insumo", description = "Registra un nuevo insumo en el inventario del restaurante")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Insumo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos — campos requeridos faltantes o stock negativo")
    })
    @PostMapping
    public ResponseEntity<Insumo> crearInsumo(@Valid @RequestBody InsumoDTO dto) {
        return new ResponseEntity<>(insumoService.crearInsumo(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los insumos", description = "Devuelve la lista completa de insumos registrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<Insumo>> obtenerInsumos() {
        return new ResponseEntity<>(insumoService.listarTodos(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener insumo por ID", description = "Busca y devuelve un insumo específico por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Insumo encontrado"),
        @ApiResponse(responseCode = "404", description = "No existe un insumo con ese ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Insumo> obtenerPorId(
            @Parameter(description = "ID del insumo a buscar", example = "1")
            @PathVariable Long id) {
        return new ResponseEntity<>(insumoService.obtenerPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un insumo", description = "Modifica los datos de un insumo existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Insumo actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en el cuerpo de la solicitud"),
        @ApiResponse(responseCode = "404", description = "No existe un insumo con ese ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Insumo> actualizarInsumo(
            @Parameter(description = "ID del insumo a actualizar", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody InsumoDTO dto) {
        return new ResponseEntity<>(insumoService.actualizarInsumo(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un insumo", description = "Elimina permanentemente un insumo del inventario")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Insumo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "No existe un insumo con ese ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInsumo(
            @Parameter(description = "ID del insumo a eliminar", example = "1")
            @PathVariable Long id) {
        insumoService.eliminarInsumo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
