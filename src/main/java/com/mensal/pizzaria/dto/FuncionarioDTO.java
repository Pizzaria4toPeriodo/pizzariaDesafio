package com.mensal.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {
    private Long id;

    @NotBlank(message = "O campo nao pode ser nulo")
    @Size(min = 5, max = 40, message = "O nome de o funcionario deve conter entre 10-50 caracteres")
    private String nomeFuncionario;

    @NotBlank(message = "O campo nao pode ser nulo")
    @Size(min = 5, max = 40, message = "O cargo deve conter entre 10-50 caracteres")
    private String cargo;
}