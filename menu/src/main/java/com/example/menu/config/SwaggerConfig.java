package com.example.menu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Microservicio de Menú")
                        .version("1.0.0")
                        .description("Documentación oficial del microservicio encargado de la gestión de categorías y productos del restaurante.")
                        .contact(new Contact()
                                .name("Jorge Cañas")));
    }
}