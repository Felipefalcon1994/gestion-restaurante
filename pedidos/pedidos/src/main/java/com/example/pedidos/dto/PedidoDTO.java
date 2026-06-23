package com.example.pedidos.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoDTO {
    @NotNull(message = "El ID del usuario es obligatorio")
    @Schema(description = "Id usuario que realiza el pedido", example = "24")
    private Long usuarioIdExterno;

    @NotNull(message = "El ID del producto es obligatorio")
    @Schema(description ="Id de producto", example = "234")
    private Long productoIdExterno;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de productos", example = "4")
    private Integer cantidad;
}