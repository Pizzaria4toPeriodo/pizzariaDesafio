package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.ClienteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteTest {
    @Test
    void entityTest() {
        ClienteEntity cliente = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        Assertions.assertEquals(1L, cliente.getId());
        Assertions.assertEquals("Gustavo", cliente.getNomeCliente());
        Assertions.assertEquals("36126170601", cliente.getCpf());
        Assertions.assertNull(cliente.getEnderecoList());
        Assertions.assertEquals("+55 45 99988-7766", cliente.getTelefone());
    }

    @Test
    void entitySetterTest() {
        ClienteEntity cliente = new ClienteEntity();

        cliente.setId(1L);
        cliente.setNomeCliente("Gustavo");
        cliente.setCpf("36126170601");
        cliente.setEnderecoList(null);
        cliente.setTelefone("+55 45 99988-7766");

        Assertions.assertEquals(1L, cliente.getId());
        Assertions.assertEquals("Gustavo", cliente.getNomeCliente());
        Assertions.assertEquals("36126170601", cliente.getCpf());
        Assertions.assertNull(cliente.getEnderecoList());
        Assertions.assertEquals("+55 45 99988-7766", cliente.getTelefone());
    }
}