package com.example.pagos.model;

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
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @NotNull(message = "El ID del pedido es obligatorio")
    @Column(name = "pedido_id_externo", nullable = false)
    private Long pedidoIdExterno;

    @NotNull(message = "El monto es obligatorio")
    @Column(nullable = false)
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estado;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    public enum MetodoPago {
        EFECTIVO, DEBITO, CREDITO, TRANSFERENCIA
    }

    public enum EstadoPago {
        PENDIENTE, COMPLETADO, RECHAZADO
    }

}
