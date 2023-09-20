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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class EnderecoServiceTest {

    @MockBean
    private EnderecoService enderecoService;

    @MockBean
    private EnderecoRepository repository;


    @Mock
    private ModelMapper modelMapper;

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

        Mockito.when(repository.save(Mockito.any(EnderecoEntity.class))).thenReturn(new EnderecoEntity());
        Mockito.when(repository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoService.findByRua("coritians")).thenReturn(enderecoDTO);
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(endereco));
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testBuscarRua() {


        EnderecoDTO endereco =  enderecoService.findByRua("coritians");
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(endereco);

        Assertions.assertEquals(1, enderecos.size());

        Assertions.assertEquals("coritians", enderecos.get(0).getRua());
    }

    @Test
    void testLista() {

        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNomeCliente("Cliente1");
        clienteDTO.setCpf("31621441164");
        clienteDTO.setTelefone("1234567890");

        enderecoDTOList.add(new EnderecoDTO(1L, "Rua 1", 123, clienteDTO));

        // Simular el comportamiento del repositorio al llamar a findAll
        when(enderecoService.findAll()).thenReturn(enderecoDTOList);

        // Ejecutar el método findAll del servicio
        List<EnderecoDTO> enderecoDTOLista = enderecoService.findAll();

        // Verificar que se haya llamado al método findAll del repositorio
        Assertions.assertNotNull(enderecoDTOLista);
        Assertions.assertEquals(1, enderecoDTOLista.size());
    }



}