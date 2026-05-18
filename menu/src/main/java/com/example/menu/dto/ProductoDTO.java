// Aqui se crea esta clase con el fin pedir al usuario que pase la Id
// de la categoria en vez de la categoria entera

package com.example.menu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Integer precio;

    private Boolean disponible = true;

    //Aqui es donde pedimos el ID de la categoria para hacer la relacion

    @NotNull(message = "Debe especificar el ID de la categoria")
    private Long idCategoria;
    
}

