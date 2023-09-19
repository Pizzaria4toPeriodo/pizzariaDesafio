package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
<<<<<<< HEAD
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;
>>>>>>> 89d15734e6678d16d002998f3903345b3cbf1544

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService service;

    private ObjectMapper objectMapper;
    private ProdutoDTO produtoValido;
<<<<<<< HEAD
    private ProdutoDTO produtoInvalido;
=======
>>>>>>> 89d15734e6678d16d002998f3903345b3cbf1544

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
<<<<<<< HEAD
        produtoValido = new ProdutoDTO(1L, "Batata", 2.50, null);
        produtoValido = new ProdutoDTO();
=======
        produtoValido = new ProdutoDTO(1L, "Batata", 2.50);
>>>>>>> 89d15734e6678d16d002998f3903345b3cbf1544
    }

    @Test
    void findByNomeProdutoTest() throws Exception {
        ProdutoDTO produtoEncontrado = new ProdutoDTO();
        produtoEncontrado.setNomeProduto(produtoValido.getNomeProduto());

        when(service.findByNomeProduto(produtoValido.getNomeProduto())).thenReturn(produtoEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/" + produtoValido.getNomeProduto()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(produtoEncontrado)));
    }

    @Test
    void findAllTest() throws Exception {
        List<ProdutoDTO> produtoList = new ArrayList<>();
        produtoList.add(produtoValido);
        when(service.findAll()).thenReturn(produtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/list")).andExpect(status().isOk());
    }

    @Test
    void createTest() throws Exception {
        when(service.create(any(ProdutoDTO.class))).thenReturn(produtoValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoValido)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomeProduto").value("Batata")).andReturn();
    }

    @Test
    void updateTest() throws Exception {
<<<<<<< HEAD
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Batata", 7.5, null);
=======
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Batata", 7.5);
>>>>>>> 89d15734e6678d16d002998f3903345b3cbf1544

        when(service.update(produtoDTO.getId(), produtoDTO)).thenReturn(produtoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/produtos/{id}", produtoDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", id))
                .andExpect(status().is(500));
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 89d15734e6678d16d002998f3903345b3cbf1544
