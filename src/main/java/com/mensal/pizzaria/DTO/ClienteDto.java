package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.Endereco;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ClienteDto {



    private Long id;

    private String nome;

    private List<Endereco> id_endereco;

    private String telefone;




    public ClienteDto(Long id, String nome, List<Endereco> id_endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.id_endereco = id_endereco;
        this.telefone = telefone;
    }

    public ClienteDto() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereco> getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(List<Endereco> id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
