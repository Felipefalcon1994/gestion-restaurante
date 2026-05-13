package com.example.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventario.model.RecetaDetalle;

@Repository
public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Long> {
    
}
