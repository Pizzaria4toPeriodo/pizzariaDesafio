package com.mensal.pizzaria.DTO;

import com.mensal.pizzaria.Entity.Funcionario;
import com.mensal.pizzaria.Entity.Pedido;

public class EntregaDTO {

    private  Long id;

    private Funcionario entregador;

    private Pedido pedido;

    private String statusPedido;

    private  String formaPagamento;

    private int valorEntrega;


    public EntregaDTO(){}
    public EntregaDTO(Long id, Funcionario entregador, Pedido pedido, String statusPedido, String formaPagamento, int valorEntrega) {
        this.id = id;
        this.entregador = entregador;
        this.pedido = pedido;
        this.statusPedido = statusPedido;
        this.formaPagamento = formaPagamento;
        this.valorEntrega = valorEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getEntregador() {
        return entregador;
    }

    public void setEntregador(Funcionario entregador) {
        this.entregador = entregador;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(int valorEntrega) {
        this.valorEntrega = valorEntrega;
    }
}
