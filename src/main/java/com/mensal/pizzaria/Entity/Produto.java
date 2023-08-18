package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "produto_table", schema = "pizzaria")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,  unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos;
}
