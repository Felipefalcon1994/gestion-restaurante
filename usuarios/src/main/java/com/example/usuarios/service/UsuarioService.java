package com.example.usuarios.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.usuarios.dto.UsuarioDTO;
import com.example.usuarios.model.Rol;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.RolRepository;
import com.example.usuarios.repository.UsuarioRepository;

import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UsuarioService {

    private final WebClient.Builder webClientBuilder;
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, WebClient.Builder webClientBuilder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Usuario crearUsuario(UsuarioDTO dto) {
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("No se puede crear usuario: Rol no encontrado con el ID: " + dto.getIdRol()));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(dto.getPassword()); // En un entorno real, esto iría encriptado con BCrypt
        usuario.setRol(rol);

        Usuario guardado = usuarioRepository.save(usuario);
        log.info("Usuario registrado exitosamente: {} con Rol: {}", guardado.getNombre(), rol.getNombre());
        return guardado;
    }

    public List<Usuario> listarTodos() {
        log.info("Consultando la lista completa de usuarios");
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + id));
    }

    public Usuario actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuarioExistente = obtenerPorId(id);
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("No se puede actualizar: Rol no encontrado con el ID: " + dto.getIdRol()));

        usuarioExistente.setNombre(dto.getNombre());
        usuarioExistente.setCorreo(dto.getCorreo());
        usuarioExistente.setPassword(dto.getPassword());
        usuarioExistente.setRol(rol);

        log.info("Actualizando usuario ID: {}", id);
        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = obtenerPorId(id);
        usuarioRepository.delete(usuario);
        log.info("Usuario dado de baja ID: {}", id);
    }

    public Object obtenerPedidosDeUsuario(Long idUsuario) {
        log.info("Iniciando comunicación con microservicio de Pedidos para el usuario ID: {}", idUsuario);

        Usuario usuario = obtenerPorId(idUsuario);

        try{
            return webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8084/api/pedidos/usuario/" + idUsuario)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (Exception e) {
            log.error("Fallo la comunicación con el microservicio de Pedidos: {}", e.getMessage());
            throw new RuntimeException("Servicio de Pedidos no disponible en este momento.");
            }
        }
    }