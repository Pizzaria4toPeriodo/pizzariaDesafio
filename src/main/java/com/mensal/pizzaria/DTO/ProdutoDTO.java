package com.mensal.pizzaria.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private String tipo;
    private BigDecimal preco;
}
