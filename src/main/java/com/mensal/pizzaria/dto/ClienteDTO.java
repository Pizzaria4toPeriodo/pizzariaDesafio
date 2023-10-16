package com.mensal.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "O campo não pode ser nulo")
    @Size(min = 2, max = 50, message = "O nome do cliente deve conter minimo 2 caracteres e maximo 50")
    private String nomeCliente;

    @CPF(message = "O CPF deve seguir o formato padrão")
    private String cpf;

    @NotBlank(message = "O campo nao pode ser nulo")
    private String telefone;
}