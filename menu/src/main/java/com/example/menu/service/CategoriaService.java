package com.example.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.menu.dto.CategoriaDTO;
import com.example.menu.model.Categoria;
import com.example.menu.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria crearCategoria(CategoriaDTO dto) {

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(dto.getNombre());
        nuevaCategoria.setDescripcion(dto.getDescripcion());

        return categoriaRepository.save(nuevaCategoria);
    }

    public List<Categoria> listarTodas(){
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
    }

    public Categoria actualizarCategoria(Long id, CategoriaDTO dto) {
        Categoria categoriaExistente = obtenerPorId(id);
        
        categoriaExistente.setNombre(dto.getNombre());
        categoriaExistente.setDescripcion(dto.getDescripcion());
        
        return categoriaRepository.save(categoriaExistente);
    }

    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerPorId(id);
        
        categoriaRepository.delete(categoria);
    }
    
}
