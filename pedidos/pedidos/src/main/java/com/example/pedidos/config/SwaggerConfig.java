package com.example.pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig{
    
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
        .info(new Info()
            .title("API 2026 Gestion-Restaurante PEDIDOS")
            .version("1.1.1")
            .description("Documentacion para resultados de Pedidos de Gestion-Restaurante")
        );
    }
}
