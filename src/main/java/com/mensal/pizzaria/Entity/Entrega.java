package com.mensal.pizzaria.Entity;




import  com.mensal.pizzaria.Entity.Funcionario;
import  com.mensal.pizzaria.Entity.Pedido;
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
@Table(name = "cliente_table", schema = "pizzaria")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,  unique = true)
    private  Long id;



    @OneToOne
    private Funcionario entregador;


    @OneToOne
    private  Pedido pedido;

    private String statusPedido;

    private  String formaPagamento;

    private int valorEntrega;



}
