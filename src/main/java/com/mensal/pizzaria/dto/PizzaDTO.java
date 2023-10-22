package com.mensal.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {
    private Long id;

    @NotBlank(message = "O campo nomePizza não pode estar em branco")
    private String nomePizza;

    @NotNull(message = "O campo tamanho não pode estar em branco")
    private Tamanho tamanho;

    @NotNull(message = "O campo categoria não pode estar em branco")
    private Categoria categoria;

    @JsonIgnoreProperties("pizzaList")
    @NotEmpty(message = "É necessário conter ao menos um sabor")
    private List<SaborDTO> saborList;

    @Column(nullable = false)
    private Double preco;
}
