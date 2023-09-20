package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import com.mensal.pizzaria.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private ClienteEntity clienteValido;
    private EnderecoEntity enderecoEntity;
    private EnderecoDTO enderecoDTO;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void injectData() {
        enderecoEntity = new EnderecoEntity(1L, "Lobos", 222, clienteValido);
        List<EnderecoEntity> enderecos = new ArrayList<>();
        enderecos.add(enderecoEntity);

        clienteValido = new ClienteEntity(1L, "Luis", "801.720.159-10", enderecos, "45991539849");

        enderecoDTO = new EnderecoDTO(1L,"Lobos",222,clienteDTO);
        List<EnderecoDTO> enderecosdto = new ArrayList<>();
        enderecos.add(enderecoEntity);
        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        clienteDTOList.add(new ClienteDTO(1L, "Luis", "801.720.159-10", enderecosdto, "45991539849"));

        Mockito.when(repository.save(Mockito.any(ClienteEntity.class))).thenReturn(new ClienteEntity());
        Mockito.when(repository.save(clienteValido)).thenReturn(clienteValido);
        Mockito.when(clienteService.create(clienteDTO)).thenReturn(clienteDTO);
        Mockito.when(clienteService.findCpf("801.720.159-10")).thenReturn(clienteDTO);
        Mockito.when(clienteService.findAll()).thenReturn(clienteDTOList);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testByCpf() {
        ClienteDTO cliente = clienteService.findCpf("801.720.159-10");
        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(cliente);

        Assertions.assertEquals(1, clientes.size());

    }
    @Test
    void testLista() {

        List<ClienteDTO> clienteDTOLista = clienteService.findAll();

        Assertions.assertNotNull(clienteDTOLista);
        Assertions.assertEquals(1, clienteDTOLista.size());
    }

    @Test
    void testCreateCliente() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Luis", "80172015910", null, "45991539849");

        when(clienteService.create(any(ClienteDTO.class))).thenReturn(clienteDTO);

        ClienteDTO createdClienteDTO = clienteService.create(clienteDTO);

        verify(clienteService, times(1)).create(any(ClienteDTO.class));

        Assertions.assertNotNull(createdClienteDTO);
        Assertions.assertEquals(1L, createdClienteDTO.getId());
        Assertions.assertEquals("Luis", createdClienteDTO.getNomeCliente());
        Assertions.assertEquals("80172015910", createdClienteDTO.getCpf());
        Assertions.assertEquals("45991539849", createdClienteDTO.getTelefone());
    }
    @Test
    void testUpdate() {
        Long id = 1L;

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(1L);
        enderecoDTO.setRua("ruanova");
        enderecoDTO.setNumero(555);

        ClienteDTO clienteAtualizada = new ClienteDTO(id, "Mario", "80172015910", null, "45991539849");

        clienteService.update(id, clienteAtualizada);

        Assertions.assertEquals("Mario", clienteAtualizada.getNomeCliente());
        Assertions.assertEquals("80172015910", clienteAtualizada.getCpf());
        Assertions.assertEquals("45991539849", clienteAtualizada.getTelefone());
    }
}
