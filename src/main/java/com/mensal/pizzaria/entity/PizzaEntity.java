package com.mensal.pizzaria.entity;

import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pizza", schema = "pizzaria")
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nomePizza;

    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(name = "pizza_sabor", schema = "pizzaria", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "sabor_id"))
    private List<SaborEntity> saborList;

    @ManyToMany(mappedBy = "pizzaList")
    private List<PedidoEntity> pedidoList;

    @Column(nullable = false)
    private Double preco;
}
