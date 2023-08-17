package com.mensal.pizzaria.DTO;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private String tipo;
    private BigDecimal preco;

    public ProdutoDTO() {}

    public ProdutoDTO(Long id, String nome, String tipo, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
