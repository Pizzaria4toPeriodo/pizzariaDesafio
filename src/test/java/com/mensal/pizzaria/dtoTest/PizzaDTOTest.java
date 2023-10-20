package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.PizzaDTO;
import com.mensal.pizzaria.dto.SaborDTO;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class PizzaDTOTest {
    @Test
    void testDTO() {
        SaborDTO sabor = new SaborDTO();

        PizzaDTO pizza = new PizzaDTO(1L, "Calabresa", Tamanho.M, Categoria.TRADICIONAL, Collections.singletonList(sabor), 25.0);

        Assertions.assertEquals(1L, pizza.getId());
        Assertions.assertEquals("Calabresa", pizza.getNomePizza());
        Assertions.assertEquals(Tamanho.M, pizza.getTamanho());
        Assertions.assertEquals(Categoria.TRADICIONAL, pizza.getCategoria());
        Assertions.assertEquals(Collections.singletonList(sabor), pizza.getSaborList());
        Assertions.assertEquals(25.0, pizza.getPreco());
    }

    @Test
    void testDTOSetter() {
        PizzaDTO pizza = new PizzaDTO();
        SaborDTO sabor = new SaborDTO();

        pizza.setId(1L);
        pizza.setNomePizza("Calabresa");
        pizza.setTamanho(Tamanho.M);
        pizza.setCategoria(Categoria.TRADICIONAL);
        pizza.setSaborList(Collections.singletonList(sabor));
        pizza.setPreco(25.0);

        Assertions.assertEquals(1L, pizza.getId());
        Assertions.assertEquals("Calabresa", pizza.getNomePizza());
        Assertions.assertEquals(Tamanho.M, pizza.getTamanho());
        Assertions.assertEquals(Categoria.TRADICIONAL, pizza.getCategoria());
        Assertions.assertEquals(Collections.singletonList(sabor), pizza.getSaborList());
        Assertions.assertEquals(25.0, pizza.getPreco());
    }
}
