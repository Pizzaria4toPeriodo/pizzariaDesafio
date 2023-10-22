package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.controller.EnderecoController;
import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class EnderecoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private EnderecoController controller;
    @Mock
    private EnderecoService service;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private EnderecoDTO dto;
    private EnderecoEntity entity;
    private List<EnderecoEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        dto = new EnderecoDTO();
        dto.setId(id);
        dto.setRua("Guarani");
        dto.setNumero(1);
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNomeCliente("Gustavo");
        dto.setCliente(clienteDTO);

        entity = new EnderecoEntity();
        entity.setId(id);
        entity.setRua("Guarani");
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNomeCliente("Gustavo");
        entity.setCliente(clienteEntity);

        entityList = new ArrayList<>();
        entityList.add(entity);
    }

    @Test
    void shouldCreate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/enderecos/").content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void shouldGetById() throws Exception {
        when(service.getById(id)).thenReturn(entity);
        mockMvc.perform(get("/enderecos/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldGetByRua() throws Exception {
        when(service.getByRua("Guarani")).thenReturn(entity);
        mockMvc.perform(get("/enderecos/rua/{rua}", "Guarani")).andExpect(status().isOk());
    }

    @Test
    void shouldGetByNomeCliente() throws Exception {
        when(service.getEnderecosByNomeCliente("Gustavo")).thenReturn(entityList);
        mockMvc.perform(get("/enderecos/cliente/{nomeCLiente}", "Gustavo")).andExpect(status().isOk());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/enderecos/")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/enderecos/{id}", id).content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/enderecos/{id}", id)).andExpect(status().isOk());
    }
}
