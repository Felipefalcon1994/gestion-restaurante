package com.example.inventario.dto;

import lombok.Data;

@Data 
public class ProductoMenuDTO {
    private Long id;
    private String nombre;
    private Double precio;
}