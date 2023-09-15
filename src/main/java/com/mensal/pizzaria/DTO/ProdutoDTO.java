package com.mensal.pizzaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
}
