package com.example.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventario.model.Insumo;


public interface InsumoRepository extends JpaRepository<Insumo, Long>{
    
}
