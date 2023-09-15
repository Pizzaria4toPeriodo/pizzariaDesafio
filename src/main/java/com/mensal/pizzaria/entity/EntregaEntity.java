package com.mensal.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entrega_table", schema = "pizzaria")
public class EntregaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pedido")
    private FuncionarioEntity entregador;

    @OneToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    private String statusPedido;

    private String formaPagamento;

    private int valorEntrega;


}
