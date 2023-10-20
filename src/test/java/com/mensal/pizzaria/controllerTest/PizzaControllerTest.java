package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.controller.PizzaController;
import com.mensal.pizzaria.dto.PizzaDTO;
import com.mensal.pizzaria.dto.SaborDTO;
import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import com.mensal.pizzaria.service.PizzaService;
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
class PizzaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private PizzaController controller;
    @Mock
    private PizzaService service;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private PizzaDTO dto;
    private PizzaEntity entity;
    private List<PizzaEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        dto = new PizzaDTO();
        dto.setId(id);
        dto.setNomePizza("Calabresa");
        dto.setTamanho(Tamanho.M);
        dto.setCategoria(Categoria.TRADICIONAL);
        List<SaborDTO> saborDTOList = new ArrayList<>();
        SaborDTO saborDTO = new SaborDTO();
        saborDTOList.add(saborDTO);
        dto.setSaborList(saborDTOList);
        dto.setPreco(25.0);

        entity = new PizzaEntity();
        entity.setId(id);
        entity.setNomePizza("Calabresa");
        entity.setTamanho(Tamanho.M);
        entity.setCategoria(Categoria.TRADICIONAL);
        List<SaborEntity> saborEntityList = new ArrayList<>();
        SaborEntity saborEntity = new SaborEntity();
        saborEntityList.add(saborEntity);
        entity.setSaborList(saborEntityList);
        entity.setPreco(25.0);

        entityList = new ArrayList<>();
        entityList.add(entity);
    }

    @Test
    void shouldCreate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/pizzas/").content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/pizzas/")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetById() throws Exception {
        when(service.getById(id)).thenReturn(entity);
        mockMvc.perform(get("/pizzas/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldGetByNomeSabor() throws Exception {
        when(service.getByNomePizza("Calabresa")).thenReturn(entity);
        mockMvc.perform(get("/pizzas/nome/{nome}", "Calabresa")).andExpect(status().isOk());
    }

    @Test
    void shouldGetByTamanho() throws Exception {
        when(service.getByTamanho(Tamanho.M)).thenReturn(entity);
        mockMvc.perform(get("/pizzas/tamanho/{tamanho}", Tamanho.M)).andExpect(status().isOk());
    }

    @Test
    void shouldGetByCategoria() throws Exception {
        when(service.getByCategoria(Categoria.TRADICIONAL)).thenReturn(entity);
        mockMvc.perform(get("/pizzas/categoria/{categoria}", Categoria.TRADICIONAL)).andExpect(status().isOk());
    }

    @Test
    void shouldUpdate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/pizzas/{id}", id).content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/pizzas/{id}", id)).andExpect(status().isOk());
    }
}
