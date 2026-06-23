package com.example.menu.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.menu.dto.CategoriaDTO;
import com.example.menu.model.Categoria;
import com.example.menu.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria crearCategoria(CategoriaDTO dto) {
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(dto.getNombre());
        nuevaCategoria.setDescripcion(dto.getDescripcion());

        Categoria guardada = categoriaRepository.save(nuevaCategoria);
        log.info("Categoría creada exitosamente: {} con ID: {}", guardada.getNombre(), guardada.getIdCategoria());
        return guardada;
    }

    public List<Categoria> listarTodas(){
        log.info("Consultando la lista completa de categorías");
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con el ID: " + id));
    }

    public Categoria actualizarCategoria(Long id, CategoriaDTO dto) {
        Categoria categoriaExistente = obtenerPorId(id);
        categoriaExistente.setNombre(dto.getNombre());
        categoriaExistente.setDescripcion(dto.getDescripcion());
        
        log.info("Actualizando categoría ID: {}", id);
        return categoriaRepository.save(categoriaExistente);
    }

    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerPorId(id);
        categoriaRepository.delete(categoria);
        log.info("Categoría eliminada ID: {}", id);
    }
}