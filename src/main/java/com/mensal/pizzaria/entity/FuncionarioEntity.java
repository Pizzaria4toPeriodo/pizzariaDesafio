package com.mensal.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_funcionario", schema = "pizzaria")
@NoArgsConstructor
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nomeFuncionario;

    @OneToMany(mappedBy = "funcionario")
    private List<PedidoEntity> pedidoList;
}