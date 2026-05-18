package com.example.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "insumos")
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInsumo;

    @NotBlank(message = "El nombre del insumo es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El stock actual es obligatoria")
    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @NotNull(message = "El stock actual es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual;

    @NotNull(message = "El stock minimo es obligatorio")
    @Min(value = 0, message = "El stock minimo no puede ser negativo")
    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;
    
}
