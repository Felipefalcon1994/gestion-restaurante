package com.example.menu.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.menu.dto.ProductoDTO;
import com.example.menu.model.Categoria;
import com.example.menu.model.Producto;
import com.example.menu.repository.CategoriaRepository;
import com.example.menu.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoService productoService;

    private ProductoDTO productoDTO;
    private Categoria categoria;
    private Producto productoGuardado;

    @BeforeEach
    void setUp() {
        productoDTO = new ProductoDTO();
        productoDTO.setNombre("Hamburguesa Doble");
        productoDTO.setPrecio(6500);
        productoDTO.setIdCategoria(1L);
        productoDTO.setDisponible(true);

        categoria = new Categoria(1L, "Hamburguesas", "Todo tipo de hamburguesas");

        productoGuardado = new Producto();
        productoGuardado.setIdProducto(10L);
        productoGuardado.setNombre("Hamburguesa Doble");
        productoGuardado.setPrecio(6500);
        productoGuardado.setCategoria(categoria);
    }

    // PRUEBA 1: Crear producto correctamente
    @Test
    void crearProducto_Exito() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        Producto resultado = productoService.crearProducto(productoDTO);

        assertNotNull(resultado);
        assertEquals("Hamburguesa Doble", resultado.getNombre());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    // PRUEBA 2: Fallo al crear producto por categoría inexistente
    @Test
    void crearProducto_FallaCategoriaNoExiste() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        productoDTO.setIdCategoria(99L); 

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class, 
            () -> productoService.crearProducto(productoDTO)
        );

        assertTrue(excepcion.getMessage().contains("Categoría no encontrada"));
        verify(productoRepository, never()).save(any(Producto.class));
    }

    // PRUEBA 3: Listar todos los productos del menú
    @Test
    void listarTodos_Exito() {
        Producto producto2 = new Producto();
        producto2.setIdProducto(11L);
        producto2.setNombre("Papas Fritas");
        
        List<Producto> listaSimulada = List.of(productoGuardado, producto2);
        when(productoRepository.findAll()).thenReturn(listaSimulada);

        List<Producto> resultado = productoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(productoRepository, times(1)).findAll();
    }

    // PRUEBA 4: Fallo al actualizar con una categoría que no existe
    @Test
    void actualizarProducto_FallaCategoriaNoExiste() {
        when(productoRepository.findById(10L)).thenReturn(Optional.of(productoGuardado));
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        
        productoDTO.setIdCategoria(99L);

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> productoService.actualizarProducto(10L, productoDTO)
        );

        assertTrue(excepcion.getMessage().contains("Categoría no encontrada"));
        verify(productoRepository, never()).save(any(Producto.class));
    }
}