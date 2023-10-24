package com.mensal.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensal.pizzaria.controller.PedidoController;
import com.mensal.pizzaria.dto.*;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.entity.enums.Forma_Pagamento;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.service.PedidoService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private PedidoController controller;
    @Mock
    private PedidoService service;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private PedidoDTO dto;
    private PedidoEntity entity;
    private List<PedidoEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        dto = new PedidoDTO();
        dto.setId(id);

        ClienteDTO clienteDTO = new ClienteDTO();
        dto.setCliente(clienteDTO);

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        dto.setFuncionario(funcionarioDTO);
        dto.setDelivery(true);
        dto.setFormaPagamento(Forma_Pagamento.CARTAO);
        dto.setTotal(65.0);

        entity = new PedidoEntity();
        entity.setId(id);

        ClienteEntity clienteEntity = new ClienteEntity();
        entity.setCliente(clienteEntity);

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        entity.setFuncionario(funcionarioEntity);

        entityList = new ArrayList<>();
        entityList.add(entity);
    }

    @Test
    void shouldCreate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/pedidos/").content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/pedidos/")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetById() throws Exception {
        when(service.getById(id)).thenReturn(entity);
        mockMvc.perform(get("/pedidos/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldGetPedidosByNomeCliente() throws Exception {
        when(service.getPedidosByNomeCliente("Gustavo")).thenReturn(entityList);
        mockMvc.perform(get("/pedidos/cliente/{nomeCliente}", "Gustavo")).andExpect(status().isOk());
    }

    @Test
    void shouldGetPedidosByNomeFuncionario() throws Exception {
        when(service.getPedidosByNomeFuncionario("Marcelo")).thenReturn(entityList);
        mockMvc.perform(get("/pedidos/funcionario/{nomeFuncionario}", "Marcelo")).andExpect(status().isOk());
    }

    @Test
    void shouldGetPedidosByDelivery() throws Exception {
        when(service.getPedidosByDelivery(true)).thenReturn(entityList);
        mockMvc.perform(get("/pedidos/delivery/{delivery}", true)).andExpect(status().isOk());
    }

    @Test
    void shouldGetPedidosByData() throws Exception {
        when(service.getPedidosByData(LocalDate.parse("2023-01-01"))).thenReturn(entityList);
        mockMvc.perform(get("/pedidos/data/{data}", "2023-01-01")).andExpect(status().isOk());
    }

    @Test
    void shouldUpdate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/pedidos/{id}", id).content(dtoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/pedidos/{id}", id)).andExpect(status().isOk());
    }
}
