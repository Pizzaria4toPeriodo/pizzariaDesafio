package com.mensal.pizzaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mensal.pizzaria.entity.enums.UserRole;
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

    @NotBlank(message = "O campo nomeFuncionario não pode estar em branco")
    @Size(min = 3, max = 30, message = "O nome do funcionário deve conter entre 3-30 caracteres")
    private String nomeFuncionario;
    @NotBlank(message = "Username não informado")
    private String username;
    @NotBlank(message = "Password não informada")
    private String password;
    @NotBlank(message = "Role não informada")
    private UserRole role;
    @JsonIgnoreProperties("funcionario")
    private List<PedidoDTO> pedidoList;
}