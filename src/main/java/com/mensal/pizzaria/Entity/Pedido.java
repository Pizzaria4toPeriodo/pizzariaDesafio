package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Produto> id_produto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente id_cliente;

    @Column(name = "delivery", nullable = false)
    private boolean delivery;

    @Enumerated(EnumType.STRING)
    private Status_producao status_producao;

    @Enumerated(EnumType.STRING)
    private Status_pagamento status_pagamento;

    @Column(name = "total", nullable = false)
    private BigDecimal total;
}
