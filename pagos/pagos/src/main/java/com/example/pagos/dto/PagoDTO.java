package com.example.pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoDTO {
    @NotNull(message = "El ID del pedido es obligatorio")
    @Schema(description = "Id del pedido asociado al pago", example = "1")
    private Long pedidoIdExterno;

    @NotNull(message = "El método de pago es obligatorio")
    @Schema(description = "Método de pago utilizado", example = "TARJETA")
    private String metodoPago;
}