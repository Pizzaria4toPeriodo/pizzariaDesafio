package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnderecoDTOTest {
    @Test
    void entityTest() {
        ClienteDTO cliente = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        EnderecoDTO endereco = new EnderecoDTO(1L, "Rua Guarani", 11, cliente);

        Assertions.assertEquals(1L, endereco.getId());
        Assertions.assertEquals("Rua Guarani", endereco.getRua());
        Assertions.assertEquals(11, endereco.getNumero());
        Assertions.assertEquals(cliente, endereco.getCliente());
    }

    @Test
    void entitySetterTest() {
        ClienteDTO cliente = new ClienteDTO();
        EnderecoDTO endereco = new EnderecoDTO();

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
