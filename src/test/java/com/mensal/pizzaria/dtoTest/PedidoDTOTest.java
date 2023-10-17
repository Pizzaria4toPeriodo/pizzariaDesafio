package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.Forma_Pagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class PedidoDTOTest {
    @Test
    void entityTest() {
        ProdutoDTO produto = new ProdutoDTO();
        ClienteDTO cliente = new ClienteDTO();

        PedidoDTO pedido = new PedidoDTO(1L, Collections.singletonList(produto), cliente, true, Forma_Pagamento.PIX, 25.0);

        Assertions.assertEquals(1L, pedido.getId());
        Assertions.assertEquals(Collections.singletonList(produto), pedido.getProdutoList());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(Forma_Pagamento.PIX, pedido.getFormaPagamento());
        Assertions.assertTrue(pedido.isDelivery());
        Assertions.assertEquals(25.0, pedido.getTotal());

    }

    @Test
    void entitySetterTest() {
        ProdutoDTO produto = new ProdutoDTO();
        ClienteDTO cliente = new ClienteDTO();
        PedidoDTO pedido = new PedidoDTO();

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
