package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.ProdutoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProdutoTest {
    @Test
    void entityTest() {
        ProdutoEntity produto = new ProdutoEntity(1L, "Pizza Calabreza", 25.0, null);

        Assertions.assertEquals(1L, produto.getId());
        Assertions.assertEquals("Pizza Calabreza", produto.getNomeProduto());
        Assertions.assertEquals(25.0, produto.getPreco());
        Assertions.assertNull(produto.getPedidoList());
    }
}