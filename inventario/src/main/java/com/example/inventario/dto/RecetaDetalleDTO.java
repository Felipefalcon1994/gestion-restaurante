package com.example.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecetaDetalleDTO {

    @NotNull(message = "La cantidad usada es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidadUsada;

    @NotNull(message = "Debe especificar el ID del producto (del menú)")
    private Long productoIdExterno;

    @NotNull(message = "Debe especificar el ID del insumo")
    private Long idInsumo;
    
}
