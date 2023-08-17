package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.Status_pagamento;
import com.mensal.pizzaria.Entity.Status_producao;

import java.math.BigDecimal;

public class PedidoDTO {
    private Long id;
    private int id_cliente;
    private boolean delivery;
    private Status_producao status_producao;
    private Status_pagamento status_pagamento;
    private BigDecimal total;
}
