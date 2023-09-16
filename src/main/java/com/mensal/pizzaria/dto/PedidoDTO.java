package com.mensal.pizzaria.dto;

import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.entity.Status_pagamento;
import com.mensal.pizzaria.entity.Status_producao;
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
