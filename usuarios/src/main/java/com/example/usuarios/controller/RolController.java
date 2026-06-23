package com.example.usuarios.controller;

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

import com.example.usuarios.dto.RolDTO;
import com.example.usuarios.model.Rol;
import com.example.usuarios.service.RolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Roles", description = "Administracion de los perfiles y permisos del sistema")
@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @Operation(summary = "Crear nuevo rol", description = "Registra un perfil de acceso en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Rol creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })

    @PostMapping
    public ResponseEntity<Rol> crearRol(@Valid @RequestBody RolDTO dto) {
        return new ResponseEntity<>(rolService.crearRol(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los roles", description = "Obtiene los diferentes perfiles configurados en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de roles obtenida")
    })

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerRoles() {
        return new ResponseEntity<>(rolService.listarTodos(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar rol por ID", description = "Busca un rol específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol encontrado"),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(
        @Parameter(description = "ID del rol", example = "1") @PathVariable Long id) {
        return new ResponseEntity<>(rolService.obtenerPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Actualizar rol", description = "Modifica el nombre o descripción de un rol.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol actualizado"),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(
        @Parameter(description = "ID del rol a actualizar", example = "1") @PathVariable Long id, @Valid @RequestBody RolDTO dto) {
        return new ResponseEntity<>(rolService.actualizarRol(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar rol", description = "Elimina un rol del sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Rol eliminado"),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(
        @Parameter(description = "ID del rol a eliminar", example = "1") @PathVariable Long id) {
        rolService.eliminarRol(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
