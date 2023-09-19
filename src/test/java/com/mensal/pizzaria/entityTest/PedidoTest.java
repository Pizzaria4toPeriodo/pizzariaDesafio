package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class PedidoTest {
    @Test
    void entityTest() {
        ProdutoEntity produto = new ProdutoEntity(1L, "Pizza Calabreza", 25.0, null);
        ClienteEntity cliente = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        PedidoEntity pedido = new PedidoEntity(1L, Collections.singletonList(produto), cliente, false, Forma_Pagamento.PIX, 25.0);

        Assertions.assertEquals(1L, pedido.getId());
        Assertions.assertEquals(Collections.singletonList(produto), pedido.getProdutoList());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(Forma_Pagamento.PIX, pedido.getFormaPagamento());
        Assertions.assertEquals(25.0, pedido.getTotal());

    }
}