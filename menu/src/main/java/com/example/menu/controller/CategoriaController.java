package com.example.menu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.menu.dto.CategoriaDTO;
import com.example.menu.model.Categoria;
import com.example.menu.service.CategoriaService;

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


@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorías del menú")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }
    
    @Operation(summary = "Crear nueva categoria", description = "Registra una nueva categoria en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria creada con exito"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    })
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoriaCreada = categoriaService.crearCategoria(categoriaDTO);
        return new ResponseEntity<>(categoriaCreada, HttpStatus.CREATED);
        
    }

    @Operation(summary = "Obtener todas las categorías", description = "Retorna una lista con todas las categorías registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente")
    })

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        List<Categoria> lista = categoriaService.listarTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @Operation(summary = "Buscar categoría por ID", description = "Busca una categoría específica mediante su identificador único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(
        @Parameter(description = "ID de la categoria", example = "1")@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar categoría", description = "Modifica los datos de una categoría existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
        @Parameter(description = "ID de la categoría a actualizar", example = "1")@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, dto);
        return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría del sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoría eliminada con éxito"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(
        @Parameter(description = "ID de la categoría a eliminar", example = "1") @PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
