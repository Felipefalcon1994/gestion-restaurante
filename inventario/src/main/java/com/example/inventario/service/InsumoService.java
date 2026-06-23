package com.example.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.inventario.dto.InsumoDTO;
import com.example.inventario.model.Insumo;
import com.example.inventario.repository.InsumoRepository;

@Service
public class InsumoService {
    
    private final InsumoRepository insumoRepository;

    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public Insumo crearInsumo(InsumoDTO dto) {
        if (dto.getStockActual() < 0 || dto.getStockMinimo() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        Insumo insumo = new Insumo();
        insumo.setNombre(dto.getNombre());
        insumo.setUnidadMedida(dto.getUnidadMedida());
        insumo.setStockActual(dto.getStockActual());
        insumo.setStockMinimo(dto.getStockMinimo());
        return insumoRepository.save(insumo);
    }

    public List<Insumo> listarTodos() {
        return insumoRepository.findAll();
    }

    public Insumo obtenerPorId(Long id) {
        return insumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado con el ID: " + id));
    }

    public Insumo actualizarInsumo(Long id, InsumoDTO dto) {
        Insumo insumoExistente = obtenerPorId(id);
        
        insumoExistente.setNombre(dto.getNombre());
        insumoExistente.setUnidadMedida(dto.getUnidadMedida());
        insumoExistente.setStockActual(dto.getStockActual());
        insumoExistente.setStockMinimo(dto.getStockMinimo());
        
        return insumoRepository.save(insumoExistente);
    }

    public void eliminarInsumo(Long id) {
        Insumo insumo = obtenerPorId(id);
        insumoRepository.delete(insumo);
    }
}
