package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "produto_table", schema = "pizzaria")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,  unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos;
}
