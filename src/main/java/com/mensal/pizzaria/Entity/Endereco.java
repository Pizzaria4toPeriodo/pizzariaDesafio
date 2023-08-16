package com.mensal.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereco_table", schema = "pizzaria")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "id", nullable = false,  unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "rua", nullable = false)
    private String rua;

    @Getter @Setter
    @Column(name = "numero", nullable = false)
    private int numero;


}
