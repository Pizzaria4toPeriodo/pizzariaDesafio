package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.ClienteEntity;
import com.mensal.pizzaria.Entity.ProdutoEntity;
import com.mensal.pizzaria.Entity.Status_pagamento;
import com.mensal.pizzaria.Entity.Status_producao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private List<ProdutoEntity> produtoList;
    private ClienteEntity cliente;
    private boolean delivery;
    private Status_producao statusProducao;
    private Status_pagamento statusPagamento;
    private BigDecimal total;
}
