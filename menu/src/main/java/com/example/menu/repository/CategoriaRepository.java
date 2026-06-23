package com.example.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.menu.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    
}
