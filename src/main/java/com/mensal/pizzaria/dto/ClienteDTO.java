package com.mensal.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nomeCliente;
    private String cpf;
    private List<EnderecoDTO> id_enderecoEntity;
    private String telefone;
}
