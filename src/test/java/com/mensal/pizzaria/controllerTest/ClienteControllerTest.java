package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.controller.ClienteController;
import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private ClienteController controller;
    @Mock
    private ClienteService service;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private ClienteDTO dto;
    private ClienteEntity entity;
    private List<ClienteEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        dto = new ClienteDTO();
        dto.setId(id);
        dto.setCpf("47917474534");

        entity = new ClienteEntity();
        entity.setId(id);
        entity.setCpf("47917474534");

        entityList = new ArrayList<>();
        entityList.add(entity);
    }

    @Test
    void shouldCreate() throws Exception {
        String areaDTOJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/cliente/").content(areaDTOJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void shouldGetById() throws Exception {
        when(service.getById(id)).thenReturn(entity);
        mockMvc.perform(get("/cliente/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldGetByCpf() throws Exception {
        when(service.getByCpf("47917474534")).thenReturn(entity);
        mockMvc.perform(get("/cliente/cpf/{cpf}", "47917474534")).andExpect(status().isOk());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/cliente/list")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdate() throws Exception {
        String areaDTOJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/cliente/{id}", id).content(areaDTOJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/cliente/{id}", id)).andExpect(status().isOk());
    }
}
