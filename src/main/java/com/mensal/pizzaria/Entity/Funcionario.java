package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionario_table", schema = "pizzaria")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = " nome", nullable = false, length = 30)
    private String nome;

    @Getter @Setter
    @Column(name = "cargo", nullable = false, length = 30)
    private String cargo;

}
