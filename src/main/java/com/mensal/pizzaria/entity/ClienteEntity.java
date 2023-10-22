package com.mensal.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_cliente", schema = "pizzaria")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<EnderecoEntity> enderecoList;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoEntity> pedidoList;
}
