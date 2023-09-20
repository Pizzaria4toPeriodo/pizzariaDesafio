package com.mensal.pizzaria.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.service.ClienteService;
import com.mensal.pizzaria.service.ProdutoService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;


    private ObjectMapper objectMapper;

    private ClienteDTO clienteValido;

    private EnderecoDTO enderecoDTO;


    @BeforeEach
    void setup(){

        objectMapper = new ObjectMapper();
        enderecoDTO = new EnderecoDTO(1L,"Lobos",222,clienteValido);
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(enderecoDTO);
        clienteValido = new ClienteDTO(1L,"Luis","801.720.159-10",enderecos,"45991539849");
    }


    @Test
    void findAllTest() throws Exception{

        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        clienteDTOList.add(clienteValido);
        when(service.findAll()).thenReturn(clienteDTOList);
        List<ClienteDTO> result = service.findAll();
        Assertions.assertEquals(clienteDTOList, result);
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/list")).andExpect(status().isOk());

    }


    @Test
    void  findByIdTest() throws  Exception{

        when(service.findById(1L)).thenReturn(clienteValido);

        ClienteDTO result = service.findById(1L);
        Assertions.assertEquals(clienteValido,result);

    }



}
