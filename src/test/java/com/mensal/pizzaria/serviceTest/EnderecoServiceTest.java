package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import com.mensal.pizzaria.repository.EnderecoRepository;
import com.mensal.pizzaria.service.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class EnderecoServiceTest {

    @MockBean
    EnderecoService enderecoService;

    @MockBean
    EnderecoRepository enderecoRepository;

    @MockBean
    ClienteRepository clienteRepository;

    @BeforeEach
    void injectData() {
        ClienteEntity cliente1 = new ClienteEntity();
        cliente1.setNomeCliente("Cliente1");
        cliente1.setCpf("31621441164");
        cliente1.setTelefone("1234567890");

        EnderecoEntity endereco = new EnderecoEntity(1L, "coritians", 555, cliente1);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNomeCliente("Cliente1");
        clienteDTO.setCpf("31621441164");
        clienteDTO.setTelefone("1234567890");

        EnderecoDTO enderecoDTO = new EnderecoDTO(1L, "coritians", 555, clienteDTO);

        Mockito.when(enderecoRepository.save(Mockito.any(EnderecoEntity.class))).thenReturn(new EnderecoEntity());
        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        Mockito.when(enderecoService.findByRua("coritians")).thenReturn(Arrays.asList(endereco));
        Mockito.when(enderecoRepository.findAll()).thenReturn(Arrays.asList(endereco));
    }

    @Test
    public void testBuscarRua() {

        List<EnderecoEntity> enderecos = enderecoService.findByRua("coritians");

        Assertions.assertEquals(1, enderecos.size());

        Assertions.assertEquals("coritians", enderecos.get(0).getRua());
    }

    @Test
    public void testLista() {
        List<EnderecoEntity> resultado = enderecoRepository.findAll();
        System.out.println(resultado.size());
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
    }

    @Test
    public void Testcadastra() {
        // Crear un objeto ClienteDTO
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNomeCliente("Cliente1");
        clienteDTO.setCpf("31621441164");
        clienteDTO.setTelefone("1234567890");

        EnderecoDTO endereco1 = new EnderecoDTO(1L, "coritians", 555, clienteDTO);

        // Llamar al método para crear una dirección
        EnderecoDTO createdEnderecoDTO = enderecoService.create(endereco1);

        Assertions.assertEquals(endereco1, createdEnderecoDTO);
    }


}
