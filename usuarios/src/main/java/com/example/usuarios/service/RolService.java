package com.example.usuarios.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.usuarios.dto.RolDTO;
import com.example.usuarios.model.Rol;
import com.example.usuarios.repository.RolRepository;

@Service
public class RolService {
    
    private static final Logger log = LoggerFactory.getLogger(RolService.class);
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol crearRol(RolDTO dto) {
        Rol rol = new Rol();
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        
        Rol guardado = rolRepository.save(rol);
        log.info("Rol creado exitosamente: {}", guardado.getNombre());
        return guardado;
    }

    public List<Rol> listarTodos() {
        log.info("Consultando la lista completa de roles");
        return rolRepository.findAll();
    }

    public Rol obtenerPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con el ID: " + id));
    }

    public Rol actualizarRol(Long id, RolDTO dto) {
        Rol rolExistente = obtenerPorId(id);
        rolExistente.setNombre(dto.getNombre());
        rolExistente.setDescripcion(dto.getDescripcion());
        
        log.info("Actualizando datos del rol ID: {}", id);
        return rolRepository.save(rolExistente);
    }

    public void eliminarRol(Long id) {
        Rol rol = obtenerPorId(id);
        rolRepository.delete(rol);
        log.info("Rol eliminado del sistema ID: {}", id);
    }
}