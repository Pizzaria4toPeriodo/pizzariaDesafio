package com.mensal.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mensal.pizzaria.entity.enums.Forma_Pagamento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;

    private List<ProdutoDTO> produtoList;

    private List<PizzaDTO> pizzaList;

    @JsonIgnoreProperties("pedidoList")
    @NotNull(message = "É necessário conter um cliente")
    private ClienteDTO cliente;

    @JsonIgnoreProperties("pedidoList")
    @NotNull(message = "É necessário conter um funcionário")
    private FuncionarioDTO funcionario;

    @NotNull(message = "É necessário conter um status para delivery")
    private boolean delivery;

    @NotNull(message = "É necessário conter uma forma de pagamento")
    private Forma_Pagamento formaPagamento;

    @CreationTimestamp
    private LocalDate criadoEm;

    private Double total;
}