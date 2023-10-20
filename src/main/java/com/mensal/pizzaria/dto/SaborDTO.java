package com.mensal.pizzaria.dto;

import com.mensal.pizzaria.entity.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaborDTO {
    private Long id;

    @NotBlank(message = "O nome do sabor não pode ser nulo")
    private String nomeSabor;

    @NotNull(message = "A categoria do sabor não pode ser nula")
    private Categoria categoria;
}
