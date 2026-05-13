package com.example.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventario.model.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long>{
    
}
