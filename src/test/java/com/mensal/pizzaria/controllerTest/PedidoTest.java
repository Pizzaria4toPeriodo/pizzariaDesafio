package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.Forma_Pagamento;
import com.mensal.pizzaria.service.PedidoService;
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
class PedidoTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService service;

    private ObjectMapper objectMapper;
    private PedidoDTO pedidoValido;
    private PedidoDTO pedidoInvalido;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();

        ClienteDTO cliente = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        ProdutoDTO produto = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);

        pedidoValido = new PedidoDTO(1L, List.of(produto), cliente, true, Forma_Pagamento.PIX, 25.0);
        pedidoInvalido = new PedidoDTO();
    }

    @Test
    void findAllTest() throws Exception {
        List<PedidoDTO> pedidoList = new ArrayList<>();
        pedidoList.add(pedidoValido);
        when(service.getAll()).thenReturn(pedidoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/list")).andExpect(status().isOk());
    }

    @Test
    void createTest() throws Exception {
        when(service.create(any(PedidoDTO.class))).thenReturn(pedidoValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoValido)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L)).andReturn();
    }

    @Test
    void updateTest() throws Exception {
        ClienteDTO cliente = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        ProdutoDTO produto = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);

        PedidoDTO pedidoDTO = new PedidoDTO(1L, List.of(produto), cliente, false, Forma_Pagamento.PIX, 25.0);

        when(service.update(pedidoDTO.getId(), pedidoDTO)).thenReturn(pedidoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/pedidos/{id}", pedidoDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/pedidos/{id}", id))
                .andExpect(status().is(500));
    }
}
