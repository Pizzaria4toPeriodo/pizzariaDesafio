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

    @NotBlank(message = "O campo rua não pode ser nulo")
    @Size(min = 3, max = 50, message = "A rua deve conter entre 3-50 caracteres")
    private String rua;

    @NotNull(message = "O campo numero não pode ser nulo")
    @Positive(message = "O número deve ser positivo")
    private int numero;

    @JsonIgnoreProperties("enderecoList")
    @NotNull(message = "O campo cliente não pode ser nulo")
    private ClienteDTO cliente;
}