package com.example.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.inventario.dto.RecetaDetalleDTO;
import com.example.inventario.model.Insumo;
import com.example.inventario.model.RecetaDetalle;
import com.example.inventario.repository.InsumoRepository;
import com.example.inventario.repository.RecetaDetalleRepository;

@Service
public class RecetaDetalleService {
    
    private final RecetaDetalleRepository recetaRepository;
    private final InsumoRepository insumoRepository;

    public RecetaDetalleService(RecetaDetalleRepository recetaRepository, InsumoRepository insumoRepository) {
        this.recetaRepository = recetaRepository;
        this.insumoRepository = insumoRepository;
    }

    public RecetaDetalle crearReceta(RecetaDetalleDTO dto) {
        Insumo insumo = insumoRepository.findById(dto.getIdInsumo())
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado con el ID: " + dto.getIdInsumo()));

        RecetaDetalle receta = new RecetaDetalle();
        receta.setCantidadUsada(dto.getCantidadUsada());
        receta.setProductoIdExterno(dto.getProductoIdExterno());
        receta.setInsumo(insumo);

        return recetaRepository.save(receta);
    }

    // LEER TODOS
    public List<RecetaDetalle> listarTodas() {
        return recetaRepository.findAll();
    }

    public RecetaDetalle obtenerPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con el ID: " + id));
    }

    public RecetaDetalle actualizarReceta(Long id, RecetaDetalleDTO dto) {
        RecetaDetalle recetaExistente = obtenerPorId(id);
        
        Insumo insumo = insumoRepository.findById(dto.getIdInsumo())
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado con el ID: " + dto.getIdInsumo()));

        recetaExistente.setCantidadUsada(dto.getCantidadUsada());
        recetaExistente.setProductoIdExterno(dto.getProductoIdExterno());
        recetaExistente.setInsumo(insumo);

        return recetaRepository.save(recetaExistente);
    }

    public void eliminarReceta(Long id) {
        RecetaDetalle receta = obtenerPorId(id);
        recetaRepository.delete(receta);
    }
}
