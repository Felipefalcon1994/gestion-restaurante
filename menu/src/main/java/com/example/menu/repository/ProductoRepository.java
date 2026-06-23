package com.example.menu.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.menu.model.Producto;



public interface ProductoRepository extends JpaRepository<Producto, Long>{

    List<Producto> findByCategoria_IdCategoria(Long idCategoria);

}
