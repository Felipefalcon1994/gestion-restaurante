package com.example.cocina.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordenes_cocina")
public class OrdenCocina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @NotNull(message = "El ID del pedido es obligatorio")
    @Column(name = "pedido_id_externo", nullable = false)
    private Long pedidoIdExterno;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCocina estado;

    @Column(name = "fecha_recepcion", nullable = false)
    private LocalDateTime fechaRecepcion;

    @Column(name = "fecha_finalizacion")
    private LocalDateTime fechaFinalizacion;

    @Column(length = 500)
    private String observaciones;

    public enum EstadoCocina {
        RECIBIDO, EN_PREPARACION, LISTO
    }
}
