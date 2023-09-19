package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService service;

    private ObjectMapper objectMapper;

    private EnderecoDTO enderecoValido;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        enderecoValido = new EnderecoDTO(1L, "ruanova", 345, null);
    }

    @Test
    void findRua() throws Exception {
        EnderecoDTO enderecoEncontrado = new EnderecoDTO();
        enderecoEncontrado.setRua(enderecoValido.getRua());

        when(service.findByRua(enderecoValido.getRua())).thenReturn(enderecoEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/" + enderecoValido.getRua()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(enderecoEncontrado)));
    }

    @Test
    void findAllTest() throws Exception {
        List<EnderecoDTO> enderecoList = new ArrayList<>();
        enderecoList.add(enderecoValido);
        when(service.findAll()).thenReturn(enderecoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/list")).andExpect(status().isOk());
    }

    @Test
    void createTest() throws Exception {
        when(service.create(any(EnderecoDTO.class))).thenReturn(enderecoValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoValido)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rua").value("ruanova")).andReturn();
    }

}