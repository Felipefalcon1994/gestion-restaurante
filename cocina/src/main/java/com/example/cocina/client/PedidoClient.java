package com.example.cocina.client;

import com.example.cocina.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PedidoClient {
    private final WebClient.Builder webClientBuilder;

    @Value("${pedidos-service.url}")
    private String pedidosServiceUrl;

    public PedidoClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public PedidoDTO buscarPedidoPorId(Long id) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(pedidosServiceUrl + "/{id}", id)
                    .retrieve()
                    .bodyToMono(PedidoDTO.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }

    public void actualizarEstadoPedido(Long id, String estado) {
        try {
            webClientBuilder.build()
                    .patch()
                    .uri(pedidosServiceUrl + "/{id}/estado?estado={estado}", id, estado)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            // log error
        }
    }
}
