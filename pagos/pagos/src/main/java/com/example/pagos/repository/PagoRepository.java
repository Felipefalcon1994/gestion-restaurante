package com.example.pagos.repository;

import com.example.pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByPedidoIdExterno(Long pedidoIdExterno);

    List<Pago> findByEstado(Pago.EstadoPago estado);

    List<Pago> findByMetodoPago(Pago.MetodoPago metodoPago);
}

