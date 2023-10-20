package com.mensal.pizzaria.dto;

import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "O nome do produto não pode ser nulo")
    private String nomeProduto;

    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço de um produto não pode ser negativo")
    private Double preco;
}