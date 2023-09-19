package com.mensal.pizzaria.dto;

import com.mensal.pizzaria.entity.Status_pagamento;
import com.mensal.pizzaria.entity.Status_producao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    @NotEmpty(message = "É necessário conter ao menos um produto")
    private List<ProdutoDTO> produtoList;
    @NotNull(message = "É necessário conter um cliente")
    private ClienteDTO cliente;
    private boolean delivery;
    private Status_producao statusProducao;
    private Status_pagamento statusPagamento;
    private Double total;
}
