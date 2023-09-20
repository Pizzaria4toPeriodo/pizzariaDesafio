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
public class PedidoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService service;

    private ObjectMapper objectMapper;
    private PedidoDTO pedidoValido;
    private ProdutoDTO produtoValido;
    private ClienteDTO clienteValido;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();

        produtoValido = new ProdutoDTO();
        produtoValido.setId(1L);
        produtoValido.setNomeProduto("Banana");
        produtoValido.setPreco(4.0);

        clienteValido = new ClienteDTO();
        clienteValido.setId(1L);
        clienteValido.setNomeCliente("Cliente1");
        clienteValido.setCpf("31621441164");
        clienteValido.setTelefone("1234567890");

        pedidoValido = new PedidoDTO(1L, List.of(produtoValido), clienteValido, true, Forma_Pagamento.DINHERO, 5.0);
    }

    @Test
    void findAllTest() throws Exception {
        List<PedidoDTO> pedidoList = new ArrayList<>();
        pedidoList.add(pedidoValido);
        when(service.findAll()).thenReturn(pedidoList);

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
                .andExpect(jsonPath("$.delivery").value("true")).andReturn();
    }

    @Test
    void updateTest() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO(1L, List.of(produtoValido), clienteValido, false, Forma_Pagamento.DINHERO, 8.0);

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
