package com.example.cocina.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenCocinaDTO {
    
    @NotNull(message = "El ID del pedido es obligatorio")
    private Long pedidoIdExterno;

    private String observaciones;
}
