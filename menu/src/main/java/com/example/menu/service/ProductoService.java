package com.example.menu.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.menu.dto.ProductoDTO;
import com.example.menu.model.Categoria;
import com.example.menu.model.Producto;
import com.example.menu.repository.CategoriaRepository;
import com.example.menu.repository.ProductoRepository;

@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository){
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto crearProducto(ProductoDTO dto){
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
            .orElseThrow(() -> new IllegalArgumentException("No se puede crear el producto: Categoría no encontrada"));
        
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(dto.getNombre());
        nuevoProducto.setPrecio(dto.getPrecio());

        if (dto.getDisponible() != null){
            nuevoProducto.setDisponible(dto.getDisponible());
        }

        nuevoProducto.setCategoria(categoria);
        Producto guardado = productoRepository.save(nuevoProducto);
        
        log.info("Producto creado exitosamente: {} (Precio: ${})", guardado.getNombre(), guardado.getPrecio());
        return guardado;
    }

    public List<Producto> listarTodos(){
        log.info("Consultando el catálogo completo de productos");
        return productoRepository.findAll();
    }

    public List<Producto> listarPorCategoria(Long idCategoria){
        log.info("Filtrando productos por categoría ID: {}", idCategoria);
        return productoRepository.findByCategoria_IdCategoria(idCategoria);
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con el ID: " + id));
    }

    public Producto actualizarProducto(Long id, ProductoDTO dto) {
        Producto productoExistente = obtenerPorId(id);
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException("No se puede actualizar: Categoría no encontrada"));

        productoExistente.setNombre(dto.getNombre());
        productoExistente.setPrecio(dto.getPrecio());
        if (dto.getDisponible() != null) {
            productoExistente.setDisponible(dto.getDisponible());
        }
        productoExistente.setCategoria(categoria);

        log.info("Actualizando producto ID: {}", id);
        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        Producto producto = obtenerPorId(id);
        productoRepository.delete(producto);
        log.info("Producto eliminado del catálogo ID: {}", id);
    }
}