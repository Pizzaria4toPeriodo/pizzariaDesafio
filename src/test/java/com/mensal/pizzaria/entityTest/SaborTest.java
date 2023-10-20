package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SaborTest {
    @Test
    void entitySetterTest() {
        SaborEntity sabor = new SaborEntity();

        sabor.setId(1L);
        sabor.setNomeSabor("Refrigerante");
        sabor.setCategoria(Categoria.TRADICIONAL);

        Assertions.assertEquals(1L, sabor.getId());
        Assertions.assertEquals("Refrigerante", sabor.getNomeSabor());
        Assertions.assertEquals(Categoria.TRADICIONAL, sabor.getCategoria());
    }
}
