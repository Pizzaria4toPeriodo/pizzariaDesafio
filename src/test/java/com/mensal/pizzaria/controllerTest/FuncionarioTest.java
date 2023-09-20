package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.service.FuncionarioService;
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
public class FuncionarioTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService service;

    private ObjectMapper objectMapper;
    private FuncionarioDTO funcionarioValido;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        funcionarioValido = new FuncionarioDTO(1L, "Marcelo", "Caixa");
    }

    @Test
    void findByNomeFuncionarioTest() throws Exception {
        FuncionarioDTO funcionarioEncontrado = new FuncionarioDTO();
        funcionarioEncontrado.setNomeFuncionario(funcionarioValido.getNomeFuncionario());

        when(service.findByNomeFuncionario(funcionarioValido.getNomeFuncionario())).thenReturn(funcionarioEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/funcionarios/" + funcionarioValido.getNomeFuncionario()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(funcionarioEncontrado)));
    }

    @Test
    void findAllTest() throws Exception {
        List<FuncionarioDTO> funcionarioList = new ArrayList<>();
        funcionarioList.add(funcionarioValido);
        when(service.findAll()).thenReturn(funcionarioList);

        mockMvc.perform(MockMvcRequestBuilders.get("/funcionarios/list")).andExpect(status().isOk());
    }

    @Test
    void createTest() throws Exception {
        when(service.create(any(FuncionarioDTO.class))).thenReturn(funcionarioValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioValido)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomeFuncionario").value("Marcelo")).andReturn();
    }

    @Test
    void updateTest() throws Exception {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(1L, "Nicolas", "caixa");

        when(service.update(funcionarioDTO.getId(), funcionarioDTO)).thenReturn(funcionarioDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/funcionarios/{id}", funcionarioDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/funcionarios/{id}", id))
                .andExpect(status().is(500));
    }
}
