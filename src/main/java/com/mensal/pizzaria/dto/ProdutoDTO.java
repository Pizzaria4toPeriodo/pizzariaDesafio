package com.mensal.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "O campo nomeProduto não pode estar em branco")
    private String nomeProduto;

    @NotNull(message = "O campo preço não pode ser nulo")
    @Positive(message = "O preço de um produto não pode ser negativo")
    private Double preco;
}