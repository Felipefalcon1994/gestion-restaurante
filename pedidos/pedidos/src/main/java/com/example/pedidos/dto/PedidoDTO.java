package com.example.pedidos.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoDTO {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioIdExterno;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoIdExterno;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
}