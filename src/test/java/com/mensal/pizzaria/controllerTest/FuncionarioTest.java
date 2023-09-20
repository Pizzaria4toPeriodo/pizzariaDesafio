package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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



}
