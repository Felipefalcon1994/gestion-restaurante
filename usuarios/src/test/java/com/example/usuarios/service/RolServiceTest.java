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

import com.example.usuarios.dto.RolDTO;
import com.example.usuarios.model.Rol;
import com.example.usuarios.repository.RolRepository;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    private RolDTO rolDTO;
    private Rol rolGuardado;

    @BeforeEach
    void setUp() {
        rolDTO = new RolDTO();
        rolDTO.setNombre("MESERO");
        rolDTO.setDescripcion("Atención a mesas");

        rolGuardado = new Rol();
        rolGuardado.setIdRol(2L);
        rolGuardado.setNombre("MESERO");
        rolGuardado.setDescripcion("Atención a mesas");
    }

    @Test
    void crearRol_Exito() {
        when(rolRepository.save(any(Rol.class))).thenReturn(rolGuardado);

        Rol resultado = rolService.crearRol(rolDTO);

        assertNotNull(resultado);
        assertEquals("MESERO", resultado.getNombre());
        verify(rolRepository, times(1)).save(any(Rol.class));
    }

    @Test
    void obtenerPorId_FallaRolNoExiste() {
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class, 
            () -> rolService.obtenerPorId(99L)
        );

        assertTrue(excepcion.getMessage().contains("Rol no encontrado"));
    }
}