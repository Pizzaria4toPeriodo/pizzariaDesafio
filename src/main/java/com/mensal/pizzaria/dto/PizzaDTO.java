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

    @NotBlank(message = "O nome da pizza não pode ser nulo")
    private String nomePizza;

    @NotNull(message = "O tamanho da pizza não pode ser nulo")
    private Tamanho tamanho;

    @NotNull(message = "A categoria da pizza não pode ser nula")
    private Categoria categoria;

    @JsonIgnoreProperties("pizzaList")
    @NotEmpty(message = "É necessário conter ao menos um sabor")
    private List<SaborDTO> saborList;

    @Column(nullable = false)
    private Double preco;
}
