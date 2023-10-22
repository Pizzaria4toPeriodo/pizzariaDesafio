package com.mensal.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O campo nomeCliente não pode estar em branco")
    @Size(min = 2, max = 30, message = "O nome do cliente deve conter entre 2-30 caracteres")
    private String nomeCliente;

    @NotBlank(message = "O campo CPF não pode estar em branco")
    @CPF(message = "O CPF não é válido")
    private String cpf;

    @NotBlank(message = "O campo telefone não pode estar em branco")
    private String telefone;

    @JsonIgnoreProperties("cliente")
    private List<EnderecoDTO> enderecoList;

    @JsonIgnoreProperties("cliente")
    private List<PedidoDTO> pedidoList;
}