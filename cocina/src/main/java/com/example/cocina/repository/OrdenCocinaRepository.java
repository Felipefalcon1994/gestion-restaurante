package com.example.cocina.repository;


import com.example.cocina.model.OrdenCocina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenCocinaRepository extends JpaRepository<OrdenCocina, Long> {
    
    List<OrdenCocina> findByEstado(OrdenCocina.EstadoCocina estado);

    List<OrdenCocina> findByPedidoIdExterno(Long pedidoIdExterno);
}
