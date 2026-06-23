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

import com.example.usuarios.dto.UsuarioDTO;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Usuarios", description = "Gestion de las cuentas de acceso del personal")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Crear nuevo usuario", description = "Registra un usuario en el sistema vinculándolo a un rol existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o ID de rol inexistente")
    })

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(usuarioService.crearUsuario(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los usuarios", description = "Retorna todos los empleados registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    })

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return new ResponseEntity<>(usuarioService.listarTodos(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar usuario por ID", description = "Recupera la información de un usuario y su rol asociado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(
        @Parameter(description = "ID del usuario", example = "2") @PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.obtenerPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza el nombre, correo, contraseña o rol de un empleado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
        @Parameter(description = "ID del usuario a actualizar", example = "2") @PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar usuario", description = "Da de baja un usuario del sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(
        @Parameter(description = "ID del usuario a eliminar", example = "2") @PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Obtener historial de pedidos (Comunicación Remota)", description = "Se comunica mediante WebClient con el microservicio de Pedidos para obtener el historial del usuario.")
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<Object> obtenerPedidosDeUsuario(
        @Parameter(description = "ID del usuario", example = "1") @PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.obtenerPedidosDeUsuario(id), HttpStatus.OK);
    }

}
