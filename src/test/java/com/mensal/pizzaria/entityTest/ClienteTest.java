package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.ClienteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteTest {
    @Test
    void entitySetterTest() {
        ClienteEntity cliente = new ClienteEntity();

        cliente.setId(1L);
        cliente.setNomeCliente("Gustavo");
        cliente.setCpf("36126170601");
        cliente.setTelefone("+55 45 99988-7766");
        cliente.setEnderecoList(null);
        cliente.setPedidoList(null);

        Assertions.assertEquals(1L, cliente.getId());
        Assertions.assertEquals("Gustavo", cliente.getNomeCliente());
        Assertions.assertEquals("36126170601", cliente.getCpf());
        Assertions.assertEquals("+55 45 99988-7766", cliente.getTelefone());
        Assertions.assertNull(cliente.getEnderecoList());
        Assertions.assertNull(cliente.getPedidoList());
    }
}