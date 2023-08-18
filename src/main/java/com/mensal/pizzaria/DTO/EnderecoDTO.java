package com.mensal.pizzaria.DTO;

public class EnderecoDTO {

    private Long id;

    private String rua;

    private int numero;

    public EnderecoDTO(Long id, String rua, int numero) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
