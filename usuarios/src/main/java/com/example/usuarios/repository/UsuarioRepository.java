package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
