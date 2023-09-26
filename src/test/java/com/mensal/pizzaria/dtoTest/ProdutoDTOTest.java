package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.ProdutoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoDTOTest {
    @Test
    void entityTest() {
        ProdutoDTO produto = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);

        Assertions.assertEquals(1L, produto.getId());
        Assertions.assertEquals("Pizza Calabreza", produto.getNomeProduto());
        Assertions.assertEquals(25.0, produto.getPreco());
        Assertions.assertNull(produto.getPedidoList());
    }

    @Test
    void entitySetterTest() {
        ProdutoDTO produto = new ProdutoDTO();

        produto.setId(1L);
        produto.setNomeProduto("Pizza Calabreza");
        produto.setPreco(25.0);
        produto.setPedidoList(null);

        Assertions.assertEquals(1L, produto.getId());
        Assertions.assertEquals("Pizza Calabreza", produto.getNomeProduto());
        Assertions.assertEquals(25.0, produto.getPreco());
        Assertions.assertNull(produto.getPedidoList());
    }
}
