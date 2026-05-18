package com.example.inventario.client;

import com.example.inventario.dto.ProductoMenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MenuClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${menu-service.url}")
    private String menuServiceUrl;

    public ProductoMenuDTO obtenerProductoPorId(Long idProducto) {
        try {
            return restTemplate.getForObject(menuServiceUrl + "/" + idProducto, ProductoMenuDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}