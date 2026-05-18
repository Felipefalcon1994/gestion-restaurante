package com.example.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.menu.dto.ProductoDTO;
import com.example.menu.model.Categoria;
import com.example.menu.model.Producto;
import com.example.menu.repository.CategoriaRepository;
import com.example.menu.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository){
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto crearProducto(ProductoDTO dto){
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(dto.getNombre());
        nuevoProducto.setPrecio(dto.getPrecio());

        if (dto.getDisponible() != null){
            nuevoProducto.setDisponible(dto.getDisponible());
        }

        nuevoProducto.setCategoria(categoria);

        return productoRepository.save(nuevoProducto);
    }

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }

    public Producto actualizarProducto(Long id, ProductoDTO dto) {
        Producto productoExistente = obtenerPorId(id);
        
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        productoExistente.setNombre(dto.getNombre());
        productoExistente.setPrecio(dto.getPrecio());
        if (dto.getDisponible() != null) {
            productoExistente.setDisponible(dto.getDisponible());
        }
        productoExistente.setCategoria(categoria);

        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        Producto producto = obtenerPorId(id);
        productoRepository.delete(producto);
    }
}
