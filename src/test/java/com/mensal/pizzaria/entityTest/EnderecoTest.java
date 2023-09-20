package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnderecoTest {
    @Test
    void entityTest() {
        ClienteEntity cliente = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        EnderecoEntity endereco = new EnderecoEntity(1L, "Rua Guarani", 11, cliente);

        Assertions.assertEquals(1L, endereco.getId());
        Assertions.assertEquals("Rua Guarani", endereco.getRua());
        Assertions.assertEquals(11, endereco.getNumero());
        Assertions.assertEquals(cliente, endereco.getCliente());
    }

    @Test
    void entitySetterTest() {
        ClienteEntity cliente = new ClienteEntity();
        EnderecoEntity endereco = new EnderecoEntity(1L, "Rua Guarani", 11, cliente);

        cliente.setId(1L);
        cliente.setNomeCliente("Gustavo");
        cliente.setCpf("36126170601");
        cliente.setEnderecoList(null);
        cliente.setTelefone("+55 45 99988-7766");

        endereco.setId(1L);
        endereco.setRua("Rua Guarani");
        endereco.setNumero(11);
        endereco.setCliente(cliente);

        Assertions.assertEquals(1L, endereco.getId());
        Assertions.assertEquals("Rua Guarani", endereco.getRua());
        Assertions.assertEquals(11, endereco.getNumero());
        Assertions.assertEquals(cliente, endereco.getCliente());
    }
}