package com.mensal.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 50, message = "O nome do produto deve conter entre 3 a 50 caracteres")
    private String nomeProduto;

    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço de um produto não pode ser negativo")
    private Double preco;

    private List<PedidoDTO> pedidoList;
}