package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.Endereco;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ClienteDto {
    private Long id;
    private String nome;
    private String cpf;
    private List<Endereco> id_endereco;
    private String telefone;

}
