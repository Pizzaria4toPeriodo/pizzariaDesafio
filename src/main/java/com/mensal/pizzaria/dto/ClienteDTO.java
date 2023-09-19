package com.mensal.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "O campo nao pode ser nulo")
    private String nomeCliente;

    @CPF(message = "O CPF deve seguir o formato padrão")
    private String cpf;

    private List<EnderecoDTO> enderecoList;

    @NotBlank(message = "O campo nao pode ser nulo")
    private String telefone;
}