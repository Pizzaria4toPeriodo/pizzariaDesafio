package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "entrega_table", schema = "pizzaria")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,  unique = true)
    private  Long id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Funcionario entregador;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private  Pedido pedido;

    private String statusPedido;

    private  String formaPagamento;

    private int valorEntrega;



}
