package com.example.pedidos.client;

import com.example.pedidos.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MenuClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${menu-service.url}")
    private String menuServiceUrl;

    public MenuClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public ProductoDTO buscarProductoPorId(Long id) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(menuServiceUrl + "/{id}", id)
                    .retrieve()
                    .bodyToMono(ProductoDTO.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }
}