package com.mensal.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntregaDTO {
    private Long id;
    private FuncionarioDTO entregador;
    private PedidoDTO pedido;
    private String statusPedido;
    private String formaPagamento;
    private Double valorEntrega;
}
