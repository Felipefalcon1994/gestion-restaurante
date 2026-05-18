package com.example.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuarios.dto.RolDTO;
import com.example.usuarios.model.Rol;
import com.example.usuarios.repository.RolRepository;

@Service
public class RolService {
    
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol crearRol(RolDTO dto) {
        Rol rol = new Rol();
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        return rolRepository.save(rol);
    }

    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    public Rol obtenerPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con el ID: " + id));
    }

    public Rol actualizarRol(Long id, RolDTO dto) {
        Rol rolExistente = obtenerPorId(id);
        rolExistente.setNombre(dto.getNombre());
        rolExistente.setDescripcion(dto.getDescripcion());
        return rolRepository.save(rolExistente);
    }

    public void eliminarRol(Long id) {
        Rol rol = obtenerPorId(id);
        rolRepository.delete(rol);
    }
}
