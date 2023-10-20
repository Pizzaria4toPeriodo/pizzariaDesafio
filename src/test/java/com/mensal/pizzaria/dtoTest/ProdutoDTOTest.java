package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.ProdutoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoDTOTest {
    @Test
    void testDTO() {
        ProdutoDTO produto = new ProdutoDTO(1L, "Refrigerante", 25.0);

        Assertions.assertEquals(1L, produto.getId());
        Assertions.assertEquals("Refrigerante", produto.getNomeProduto());
        Assertions.assertEquals(25.0, produto.getPreco());
    }

    @Test
    void testDTOSetter() {
        ProdutoDTO produto = new ProdutoDTO();

        produto.setId(1L);
        produto.setNomeProduto("Refrigerante");
        produto.setPreco(25.0);

        Assertions.assertEquals(1L, produto.getId());
        Assertions.assertEquals("Refrigerante", produto.getNomeProduto());
        Assertions.assertEquals(25.0, produto.getPreco());
    }
}
