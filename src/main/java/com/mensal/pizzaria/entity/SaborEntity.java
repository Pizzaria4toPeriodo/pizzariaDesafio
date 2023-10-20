package com.mensal.pizzaria.entity;

import com.mensal.pizzaria.entity.enums.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_sabor", schema = "pizzaria")
public class SaborEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nomeSabor;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToMany(mappedBy = "saborList")
    private List<PizzaEntity> pizzaList;
}
