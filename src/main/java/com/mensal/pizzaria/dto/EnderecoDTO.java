package com.mensal.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;

    @NotBlank(message = "O campo nao pode ser nulo")
    @Size(min = 5, max = 50, message = "A rua deve conter entre 10-50 caracteres")
    private String rua;

    @NotNull(message = "O campo nao pode ser nulo")
    @Positive(message = "O numero deve ser positivo")
    private int numero;

    @JsonIgnoreProperties("enderecoList")
    @NotNull(message = "O campo n pode ser nulo")
    private ClienteDTO cliente;
}