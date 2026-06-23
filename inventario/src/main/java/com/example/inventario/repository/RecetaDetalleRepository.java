package com.example.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventario.model.RecetaDetalle;

public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Long> {
    
}
