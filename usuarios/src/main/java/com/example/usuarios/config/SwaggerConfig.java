package com.example.usuarios.config;

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
                        .title("API REST - Microservicio de Usuarios")
                        .version("1.0.0")
                        .description("Documentación oficial del microservicio encargado de la gestión de usuarios, accesos y roles para el ecosistema del restaurante.")
                        .contact(new Contact()
                                .name("Jorge Cañas")
                                .url("https://github.com/Felipefalcon1994/gestion-restaurante")));
    }
}