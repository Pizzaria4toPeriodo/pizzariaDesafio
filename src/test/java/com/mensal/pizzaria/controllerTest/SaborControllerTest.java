package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.controller.SaborController;
import com.mensal.pizzaria.dto.SaborDTO;
import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.service.SaborService;
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
class SaborControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private SaborController controller;
    @Mock
    private SaborService service;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private SaborDTO dto;
    private SaborEntity entity;
    private List<SaborEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        dto = new SaborDTO();
        dto.setId(id);
        dto.setNomeSabor("Calabresa");
        dto.setCategoria(Categoria.TRADICIONAL);

        entity = new SaborEntity();
        entity.setId(id);
        entity.setNomeSabor("Calabresa");
        entity.setCategoria(Categoria.TRADICIONAL);

        entityList = new ArrayList<>();
        entityList.add(entity);
    }

    @Test
    void shouldCreate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/sabores/").content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/sabores/")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetById() throws Exception {
        when(service.getById(id)).thenReturn(entity);
        mockMvc.perform(get("/sabores/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldGetByNomeSabor() throws Exception {
        when(service.getByNomeSabor("Calabresa")).thenReturn(entity);
        mockMvc.perform(get("/sabores/nome/{nome}", "Calabresa")).andExpect(status().isOk());
    }

    @Test
    void shouldGetByCategoria() throws Exception {
        when(service.getByCategoria(Categoria.TRADICIONAL)).thenReturn(entity);
        mockMvc.perform(get("/sabores/categoria/{categoria}", Categoria.TRADICIONAL)).andExpect(status().isOk());
    }

    @Test
    void shouldUpdate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/sabores/{id}", id).content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/sabores/{id}", id)).andExpect(status().isOk());
    }
}
