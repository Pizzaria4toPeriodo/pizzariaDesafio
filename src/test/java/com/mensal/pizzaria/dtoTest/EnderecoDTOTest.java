package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnderecoDTOTest {
    @Test
    void testDTO() {
        ClienteDTO cliente = new ClienteDTO();
        EnderecoDTO endereco = new EnderecoDTO(1L, "Rua Guarani", 11, cliente);

        Assertions.assertEquals(1L, endereco.getId());
        Assertions.assertEquals("Rua Guarani", endereco.getRua());
        Assertions.assertEquals(11, endereco.getNumero());
        Assertions.assertEquals(cliente, endereco.getCliente());
    }

    @Test
    void testDTOSetter() {
        ClienteDTO cliente = new ClienteDTO();
        EnderecoDTO endereco = new EnderecoDTO();

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
