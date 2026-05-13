package com.example.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recetas_detalle")
public class RecetaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @NotNull(message = "La cantidad usada es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Column(name = "cantidad_usada", nullable = false)
    private Integer cantidadUsada;

    @NotNull(message = "El ID del producto externo es obligatorio")
    @Column(name = "producto_id_externo", nullable = false)
    private Long productoIdExterno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insumo_id_externo", nullable = false)
    private Insumo insumo;
    
}
