package com.mensal.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {
    private Long id;

    @NotBlank(message = "O campo não pode ser vazio")
    @Size(min = 3, max = 40, message = "O nome do funcionário deve conter entre 3-40 caracteres")
    private String nomeFuncionario;

    @JsonIgnoreProperties("funcionario")
    private List<PedidoDTO> pedidoList;
}