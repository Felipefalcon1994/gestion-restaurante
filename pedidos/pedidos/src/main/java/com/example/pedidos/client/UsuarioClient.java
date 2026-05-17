package com.example.pedidos.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.pedidos.dto.UsuarioDTO;

@Component 
public class UsuarioClient {
    private final WebClient.Builder webClientBuilder;

    @Value("${usuarios-service.url}")
    private String usuariosServiceUrl;

    public UsuarioClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(usuariosServiceUrl + "/{id}", id)
                    .retrieve()
                    .bodyToMono(UsuarioDTO.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }
}
