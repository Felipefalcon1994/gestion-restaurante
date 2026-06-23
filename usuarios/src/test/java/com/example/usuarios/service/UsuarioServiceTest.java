package com.example.usuarios.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.usuarios.dto.UsuarioDTO;
import com.example.usuarios.model.Rol;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.RolRepository;
import com.example.usuarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioDTO usuarioDTO;
    private Rol rolAdmin;
    private Usuario usuarioGuardado;

    @BeforeEach
    void setUp() {
        // GIVEN: Preparamos los datos antes de cada prueba
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("Jorge Cañas");
        usuarioDTO.setCorreo("jorge@restaurante.cl");
        usuarioDTO.setPassword("12345");
        usuarioDTO.setIdRol(1L);

        rolAdmin = new Rol();
        rolAdmin.setIdRol(1L);
        rolAdmin.setNombre("ADMINISTRADOR");

        usuarioGuardado = new Usuario();
        usuarioGuardado.setIdUsuario(100L);
        usuarioGuardado.setNombre("Jorge Cañas");
        usuarioGuardado.setCorreo("jorge@restaurante.cl");
        usuarioGuardado.setRol(rolAdmin);
    }

    // PRUEBA 1: Crear usuario correctamente
    @Test
    void crearUsuario_Exito() {
        when(rolRepository.findById(1L)).thenReturn(Optional.of(rolAdmin));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        Usuario resultado = usuarioService.crearUsuario(usuarioDTO);

        assertNotNull(resultado);
        assertEquals("Jorge Cañas", resultado.getNombre());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    // PRUEBA 2: Fallo al crear por rol inexistente
    @Test
    void crearUsuario_FallaRolNoExiste() {
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());
        usuarioDTO.setIdRol(99L);

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> usuarioService.crearUsuario(usuarioDTO)
        );

        assertTrue(excepcion.getMessage().contains("Rol no encontrado"));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    // PRUEBA 3: Obtener usuario por ID con éxito
    @Test
    void obtenerPorId_Exito() {
        when(usuarioRepository.findById(100L)).thenReturn(Optional.of(usuarioGuardado));

        Usuario resultado = usuarioService.obtenerPorId(100L);

        assertNotNull(resultado);
        assertEquals("Jorge Cañas", resultado.getNombre());
        verify(usuarioRepository, times(1)).findById(100L);
    }

    // PRUEBA 4: Fallo al buscar un usuario que no existe
    @Test
    void obtenerPorId_FallaUsuarioNoExiste() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class, 
            () -> usuarioService.obtenerPorId(99L)
        );

        assertTrue(excepcion.getMessage().contains("Usuario no encontrado"));
        verify(usuarioRepository, times(1)).findById(99L);
    }
}