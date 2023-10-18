package com.mensal.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mensal.pizzaria.entity.Forma_Pagamento;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;

    @NotEmpty(message = "É necessário conter ao menos um produto")
    private List<ProdutoDTO> produtoList;

    @JsonIgnoreProperties("pedidoList")
    @NotNull(message = "É necessário conter um cliente")
    private ClienteDTO cliente;

    @JsonIgnoreProperties("pedidoList")
    @NotNull(message = "É necessário conter um funcionário")
    private FuncionarioDTO funcionario;

    @NotNull(message = "É necessário conter se é delivery")
    private boolean delivery;

    @NotNull(message = "É necessário conter uma forma de pagamento")
    private Forma_Pagamento formaPagamento;

    private Double total;
}