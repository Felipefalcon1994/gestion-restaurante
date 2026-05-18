package com.example.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuarios.dto.UsuarioDTO;
import com.example.usuarios.model.Rol;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.RolRepository;
import com.example.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public Usuario crearUsuario(UsuarioDTO dto) {
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con el ID: " + dto.getIdRol()));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(dto.getPassword()); // En un entorno real, esto iría encriptado con BCrypt
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }

    public Usuario actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuarioExistente = obtenerPorId(id);
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con el ID: " + dto.getIdRol()));

        usuarioExistente.setNombre(dto.getNombre());
        usuarioExistente.setCorreo(dto.getCorreo());
        usuarioExistente.setPassword(dto.getPassword());
        usuarioExistente.setRol(rol);

        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = obtenerPorId(id);
        usuarioRepository.delete(usuario);
    }
    
}
