package com.mensal.pizzaria.controllerTest;

import com.mensal.pizzaria.controller.PedidoController;
import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.Forma_Pagamento;
import com.mensal.pizzaria.repository.PedidoRepository;
import com.mensal.pizzaria.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PedidoControllerTest {
    @InjectMocks
    private PedidoController controller;
    @Mock
    private PedidoService service;
    @Mock
    private PedidoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private PedidoDTO dto;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ProdutoDTO produto = new ProdutoDTO(id, "Pizza Calabreza", 25.0, null);
        ClienteDTO cliente = new ClienteDTO(id, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        dto = new PedidoDTO(id, Collections.singletonList(produto), cliente, true, Forma_Pagamento.PIX, 25.0);
        List<PedidoDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);

        when(service.getById(anyLong())).thenReturn(dto);
        when(service.getAll()).thenReturn(dtoList);
        when(service.create(any(PedidoDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(PedidoDTO.class))).thenReturn(dto);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<PedidoDTO>> responseEntity = controller.getAll();

        List<PedidoDTO> dtoList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(dtoList);
    }

    @Test
    void testGetById() {
        ResponseEntity<PedidoDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testCreate() {
        ResponseEntity<PedidoDTO> response = controller.create(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testUpdate() {
        ResponseEntity<PedidoDTO> response = controller.update(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDelete() {
        ResponseEntity<HttpStatus> response = controller.delete(id);

        verify(service).deleteById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
