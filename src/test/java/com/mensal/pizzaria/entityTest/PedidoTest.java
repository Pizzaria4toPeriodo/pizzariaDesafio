package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class PedidoTest {
    @Test
    void entityTest() {
        ProdutoEntity produto = new ProdutoEntity(1L, "Pizza Calabreza", 25.0, null);
        ClienteEntity cliente = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        PedidoEntity pedido = new PedidoEntity(1L, Collections.singletonList(produto), cliente, true, Forma_Pagamento.PIX, 25.0);

        Assertions.assertEquals(1L, pedido.getId());
        Assertions.assertEquals(Collections.singletonList(produto), pedido.getProdutoList());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(Forma_Pagamento.PIX, pedido.getFormaPagamento());
        Assertions.assertTrue(pedido.isDelivery());
        Assertions.assertEquals(25.0, pedido.getTotal());

    }

    @Test
    void entitySetterTest() {
        ProdutoEntity produto = new ProdutoEntity();
        ClienteEntity cliente = new ClienteEntity();

        produto.setId(1L);
        produto.setNomeProduto("Pizza Calabreza");
        produto.setPreco(25.0);
        produto.setPedidoList(null);

        cliente.setId(1L);
        cliente.setNomeCliente("Gustavo");
        cliente.setCpf("36126170601");
        cliente.setEnderecoList(null);
        cliente.setTelefone("+55 45 99988-7766");

        PedidoEntity pedido = new PedidoEntity();

        pedido.setId(1L);
        pedido.setProdutoList(Collections.singletonList(produto));
        pedido.setCliente(cliente);
        pedido.setDelivery(true);
        pedido.setFormaPagamento(Forma_Pagamento.PIX);
        pedido.setTotal(25.0);

        Assertions.assertEquals(1L, pedido.getId());
        Assertions.assertEquals(Collections.singletonList(produto), pedido.getProdutoList());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(Forma_Pagamento.PIX, pedido.getFormaPagamento());
        Assertions.assertEquals(25.0, pedido.getTotal());
    }
}