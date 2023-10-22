package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnderecoTest {
    @Test
    void entitySetterTest() {
        ClienteEntity cliente = new ClienteEntity();
        EnderecoEntity endereco = new EnderecoEntity();

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