package com.example.cocina.client;

import com.example.cocina.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PedidoClient {
    private final WebClient webClient;

    @Value("${pedidos-service.url}")
    private String pedidosServiceUrl;

    public PedidoClient() {
        this.webClient = WebClient.create();
    }

    public PedidoDTO buscarPedidoPorId(Long id) {
        try {
            return webClient
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
            webClient
                    .patch()
                    .uri(pedidosServiceUrl + "/{id}/estado?estado={estado}", id, estado)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            
        }
    }
}
