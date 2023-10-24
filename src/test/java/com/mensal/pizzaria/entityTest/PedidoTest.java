package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.*;
import com.mensal.pizzaria.entity.enums.Forma_Pagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootTest
class PedidoTest {
    @Test
    void entitySetterTest() {
        ProdutoEntity produto = new ProdutoEntity();
        PizzaEntity pizza = new PizzaEntity();
        ClienteEntity cliente = new ClienteEntity();
        FuncionarioEntity funcionario = new FuncionarioEntity();
        PedidoEntity pedido = new PedidoEntity();

        pedido.setId(1L);
        pedido.setProdutoList(Collections.singletonList(produto));
        pedido.setPizzaList(Collections.singletonList(pizza));
        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setDelivery(true);
        pedido.setFormaPagamento(Forma_Pagamento.PIX);
        pedido.setCriadoEm(LocalDate.parse("2023-10-20"));
        pedido.setTotal(25.0);

        Assertions.assertEquals(1L, pedido.getId());
        Assertions.assertEquals(Collections.singletonList(produto), pedido.getProdutoList());
        Assertions.assertEquals(Collections.singletonList(pizza), pedido.getPizzaList());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(funcionario, pedido.getFuncionario());
        Assertions.assertEquals(Forma_Pagamento.PIX, pedido.getFormaPagamento());
        Assertions.assertTrue(pedido.isDelivery());
        Assertions.assertEquals("2023-10-20", pedido.getCriadoEm().toString());
        Assertions.assertEquals(25.0, pedido.getTotal());
    }
}