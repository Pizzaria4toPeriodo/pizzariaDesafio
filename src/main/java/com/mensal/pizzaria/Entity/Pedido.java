package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Table(name = "pedido_table", schema = "pizzaria")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,  unique = true)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private int id_cliente;

    @Column(name = "delivery", nullable = false)
    private boolean delivery;

    @Enumerated(EnumType.STRING)
    private Status_producao status_producao;

    @Enumerated(EnumType.STRING)
    private Status_pagamento status_pagamento;

    @Column(name = "total", nullable = false)
    private BigDecimal total;
}
