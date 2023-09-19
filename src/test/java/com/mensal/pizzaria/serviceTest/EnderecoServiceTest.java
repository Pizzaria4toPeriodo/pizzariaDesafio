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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EnderecoServiceTest {

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


        EnderecoEntity endereco1 = new EnderecoEntity(1L, "coritians", 555, cliente1);

        Mockito.when(enderecoRepository.save(endereco1)).thenReturn(endereco1);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco1));
        Mockito.when(enderecoRepository.findAll()).thenReturn(Arrays.asList(endereco1));
    }

    @Test
    public void testListaLembrete() {
        List<EnderecoEntity> resultado = enderecoRepository.findAll();
        System.out.println(resultado.size());
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
    }

}
