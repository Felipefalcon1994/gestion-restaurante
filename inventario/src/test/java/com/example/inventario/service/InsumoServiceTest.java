package com.example.inventario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.inventario.dto.InsumoDTO;
import com.example.inventario.model.Insumo;
import com.example.inventario.repository.InsumoRepository;

@ExtendWith(MockitoExtension.class)
class InsumoServiceTest {

    @Mock
    private InsumoRepository insumoRepository;

    @InjectMocks
    private InsumoService insumoService;

    // -------------------------------------------------------
    // Test 1 — insumo creado correctamente
    // -------------------------------------------------------
    @Test
    void crearInsumo_deberiaGuardarYRetornarInsumo() {
        
        InsumoDTO dto = new InsumoDTO();
        dto.setNombre("Harina");
        dto.setUnidadMedida("kg");
        dto.setStockActual(50);
        dto.setStockMinimo(10);

        Insumo insumoGuardado = new Insumo(1L, "Harina", "kg", 50, 10);
        when(insumoRepository.save(any(Insumo.class))).thenReturn(insumoGuardado);

        
        Insumo resultado = insumoService.crearInsumo(dto);

        
        assertNotNull(resultado);
        assertEquals("Harina", resultado.getNombre());
        assertEquals(50, resultado.getStockActual());
        verify(insumoRepository, times(1)).save(any(Insumo.class));
    }

    // -------------------------------------------------------
    // Test 2 — Caso crítico: excepción con stock negativo
    // -------------------------------------------------------
    @Test
    void crearInsumo_deberiaLanzarExcepcion_cuandoStockActualEsNegativo() {
        
        InsumoDTO dto = new InsumoDTO();
        dto.setNombre("Azúcar");
        dto.setUnidadMedida("kg");
        dto.setStockActual(-5);
        dto.setStockMinimo(10);

       
        assertThrows(IllegalArgumentException.class, () -> insumoService.crearInsumo(dto));
        verify(insumoRepository, never()).save(any(Insumo.class));
    }

    // -------------------------------------------------------
    // Test 3 — Listar todos los insumos
    // -------------------------------------------------------
    @Test
    void listarTodos_deberiaRetornarListaCompleta() {
        
        List<Insumo> listaEsperada = List.of(
                new Insumo(1L, "Harina", "kg", 50, 10),
                new Insumo(2L, "Sal", "g", 200, 50)
        );
        when(insumoRepository.findAll()).thenReturn(listaEsperada);

    
        List<Insumo> resultado = insumoService.listarTodos();

        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(insumoRepository, times(1)).findAll();
    }

    // -------------------------------------------------------
    // Test 4 — Excepción al buscar un ID inexistente
    // -------------------------------------------------------
    @Test
    void obtenerPorId_deberiaLanzarExcepcion_cuandoIdNoExiste() {
        
        Long idInexistente = 99L;
        when(insumoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        
        assertThrows(RuntimeException.class, () -> insumoService.obtenerPorId(idInexistente));
        verify(insumoRepository, times(1)).findById(idInexistente);
    }
}
