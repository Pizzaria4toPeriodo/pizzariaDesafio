package com.mensal.pizzaria.controllerTest;

import com.mensal.pizzaria.controller.ClienteController;
import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.repository.ClienteRepository;
import com.mensal.pizzaria.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteTest {
    @InjectMocks
    private ClienteController controller;
    @Mock
    private ClienteService service;
    @Mock
    private ClienteRepository repository;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ClienteDTO clienteDTO = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        clienteDTOList.add(clienteDTO);

        when(service.getById(anyLong())).thenReturn(clienteDTO);
        when(service.getByCpf(anyString())).thenReturn(clienteDTO);
        when(service.getAll()).thenReturn(clienteDTOList);
        when(service.create(any(ClienteDTO.class))).thenReturn(clienteDTO);
        when(service.update(anyLong(), any(ClienteDTO.class))).thenReturn(clienteDTO);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testDeleteCliente() {
        Long clienteId = 1L;
        
        ResponseEntity<HttpStatus> response = controller.delete(clienteId);

        verify(service).deleteById(clienteId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
