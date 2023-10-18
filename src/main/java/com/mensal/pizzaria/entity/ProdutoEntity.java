package com.mensal.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_produto", schema = "pizzaria")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nomeProduto;

    @Column(nullable = false)
    private Double preco;

    @ManyToMany(mappedBy = "produtoList")
    private List<PedidoEntity> pedidoList;
}
