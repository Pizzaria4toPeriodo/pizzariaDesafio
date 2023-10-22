package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SaborTest {
    @Test
    void entitySetterTest() {
        SaborEntity sabor = new SaborEntity();
        List<PizzaEntity> pizzaEntityList = new ArrayList<>();

        sabor.setId(1L);
        sabor.setNomeSabor("Refrigerante");
        sabor.setCategoria(Categoria.TRADICIONAL);
        sabor.setPizzaList(pizzaEntityList);

        Assertions.assertEquals(1L, sabor.getId());
        Assertions.assertEquals("Refrigerante", sabor.getNomeSabor());
        Assertions.assertEquals(Categoria.TRADICIONAL, sabor.getCategoria());
        Assertions.assertEquals(pizzaEntityList, sabor.getPizzaList());
    }
}
