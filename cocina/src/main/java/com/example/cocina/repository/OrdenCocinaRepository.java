package com.example.cocina.repository;


import com.example.cocina.model.OrdenCocina;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OrdenCocinaRepository extends JpaRepository<OrdenCocina, Long> {
    
    List<OrdenCocina> findByEstado(OrdenCocina.EstadoCocina estado);

    List<OrdenCocina> findByPedidoIdExterno(Long pedidoIdExterno);

}
