package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class PizzaTest {
    @Test
    void testEntituSetter() {
        PizzaEntity pizza = new PizzaEntity();
        SaborEntity sabor = new SaborEntity();
        List<PedidoEntity> pedidoEntityList = new ArrayList<>();

        pizza.setId(1L);
        pizza.setNomePizza("Calabresa");
        pizza.setTamanho(Tamanho.M);
        pizza.setCategoria(Categoria.TRADICIONAL);
        pizza.setSaborList(Collections.singletonList(sabor));
        pizza.setPreco(25.0);
        pizza.setPedidoList(pedidoEntityList);

        Assertions.assertEquals(1L, pizza.getId());
        Assertions.assertEquals("Calabresa", pizza.getNomePizza());
        Assertions.assertEquals(Tamanho.M, pizza.getTamanho());
        Assertions.assertEquals(Categoria.TRADICIONAL, pizza.getCategoria());
        Assertions.assertEquals(Collections.singletonList(sabor), pizza.getSaborList());
        Assertions.assertEquals(25.0, pizza.getPreco());
        Assertions.assertEquals(pedidoEntityList, pizza.getPedidoList());
    }
}
