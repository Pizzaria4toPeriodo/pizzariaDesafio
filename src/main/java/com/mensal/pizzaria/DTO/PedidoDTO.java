package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Entity.Produto;
import com.mensal.pizzaria.Entity.Status_pagamento;
import com.mensal.pizzaria.Entity.Status_producao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private Cliente id_cliente;
    private Produto id_produto;
    private boolean delivery;
    private Status_producao status_producao;
    private Status_pagamento status_pagamento;
    private BigDecimal total;
}
