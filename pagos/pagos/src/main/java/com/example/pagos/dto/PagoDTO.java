package com.example.pagos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoDTO {
    @NotNull(message = "El ID del pedido es obligatorio")
    private Long pedidoIdExterno;

    @NotNull(message = "El método de pago es obligatorio")
    private String metodoPago;
}
