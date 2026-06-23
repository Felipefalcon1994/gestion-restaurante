package com.example.menu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.menu.dto.ProductoDTO;
import com.example.menu.model.Producto;
import com.example.menu.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;






@Tag(name = "Productos", description = "Operaciones relacionadas con el catálogo de productos")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @Operation(summary = "Crear nuevo producto", description = "Registra un producto validando que su precio sea correcto y su categoría exista.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos (ej. precio negativo)")
    })

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoDTO productoDTO){
        Producto productoCreado = productoService.crearProducto(productoDTO);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los productos", description = "Retorna el catálogo completo de productos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> lista = productoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Buscar producto por ID", description = "Recupera la información detallada de un producto específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(
        @Parameter(description = "ID del producto", example = "1") @PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @Operation(summary = "Buscar productos por Categoría", description = "Filtra el catálogo devolviendo solo los productos de una categoría específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos filtrada exitosamente")
    })

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> obtenerPorCategoria(
        @Parameter(description = "ID de la categoría a filtrar", example = "1") @PathVariable Long idCategoria) {
        List<Producto> lista = productoService.listarPorCategoria(idCategoria);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar producto", description = "Modifica el precio, nombre o estado de disponibilidad de un producto.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
        @Parameter(description = "ID del producto a actualizar", example = "1") @PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        Producto productoActualizado = productoService.actualizarProducto(id, dto);
        return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto del catálogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
        @Parameter(description = "ID del producto a eliminar", example = "1") @PathVariable Long id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
