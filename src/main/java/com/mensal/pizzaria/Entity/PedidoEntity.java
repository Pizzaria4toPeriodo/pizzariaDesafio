package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "pedido_table", schema = "pizzaria")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            schema = "pizzaria",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoEntity> produtoList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @Column(nullable = false)
    private boolean delivery;

    @Enumerated(EnumType.STRING)
    @Column
    private Status_producao statusProducao;

    @Enumerated(EnumType.STRING)
    @Column
    private Status_pagamento statusPagamento;

    @Column(nullable = false)
    private BigDecimal total;
}
