package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.entity.ProdutoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProdutoTest {
    @Test
    void entitySetterTest() {
        ProdutoEntity produto = new ProdutoEntity();
        List<PedidoEntity> pedidoEntityList = new ArrayList<>();

        produto.setId(1L);
        produto.setNomeProduto("Pizza Calabreza");
        produto.setPreco(25.0);
        produto.setPedidoList(pedidoEntityList);

        Assertions.assertEquals(1L, produto.getId());
        Assertions.assertEquals("Pizza Calabreza", produto.getNomeProduto());
        Assertions.assertEquals(25.0, produto.getPreco());
        Assertions.assertEquals(pedidoEntityList, produto.getPedidoList());
    }
}