package com.example.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.menu.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    
}
