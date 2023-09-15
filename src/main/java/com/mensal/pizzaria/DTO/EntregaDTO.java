package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.Funcionario;
import com.mensal.pizzaria.Entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class EntregaDTO {
    private  Long id;
    private Funcionario entregador;
    private Pedido pedido;
    private String statusPedido;
    private  String formaPagamento;
    private int valorEntrega;
}
