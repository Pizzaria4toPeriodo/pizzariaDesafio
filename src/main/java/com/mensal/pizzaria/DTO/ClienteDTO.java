package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private List<Endereco> id_endereco;
    private String telefone;
}
