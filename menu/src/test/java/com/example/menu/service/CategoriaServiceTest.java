package com.example.menu.service;

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

import com.example.menu.dto.CategoriaDTO;
import com.example.menu.model.Categoria;
import com.example.menu.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private CategoriaDTO categoriaDTO;
    private Categoria categoriaGuardada;

    @BeforeEach
    void setUp() {
        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Bebidas");
        categoriaDTO.setDescripcion("Gaseosas y jugos");

        categoriaGuardada = new Categoria(1L, "Bebidas", "Gaseosas y jugos");
    }

    @Test
    void crearCategoria_Exito() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaGuardada);

        Categoria resultado = categoriaService.crearCategoria(categoriaDTO);

        assertNotNull(resultado);
        assertEquals("Bebidas", resultado.getNombre());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void actualizarCategoria_FallaCategoriaNoExiste() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> categoriaService.actualizarCategoria(99L, categoriaDTO)
        );

        assertTrue(excepcion.getMessage().contains("Categoría no encontrada"));
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }
}