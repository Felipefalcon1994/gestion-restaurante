package com.example.inventario.client;

import com.example.inventario.dto.ProductoMenuDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MenuClient {

    private final WebClient webClient;

    @Value("${menu-service.url}")
    private String menuServiceUrl;

    public MenuClient() {
        this.webClient = WebClient.create();
    }

    public ProductoMenuDTO obtenerProductoPorId(Long idProducto) {
        try {
            return webClient.get()
                    .uri(menuServiceUrl + "/{id}", idProducto)
                    .retrieve()
                    .bodyToMono(ProductoMenuDTO.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }
}