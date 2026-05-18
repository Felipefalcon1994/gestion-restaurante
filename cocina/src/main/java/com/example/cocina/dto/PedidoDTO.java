package com.example.cocina.dto;

import lombok.Data;

@Data   
public class PedidoDTO {
    
    private Long idPedido;
    private Long usuarioIdExterno;
    private Long productoIdExterno;
    private Integer cantidad;
    private Double total;
    private String estado;
}
