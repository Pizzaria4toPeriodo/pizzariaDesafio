package com.mensal.pizzaria.controllerTest;

import com.mensal.pizzaria.controller.EnderecoController;
import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.repository.EnderecoRepository;
import com.mensal.pizzaria.service.EnderecoService;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class EnderecoControllerTest {
    @InjectMocks
    private EnderecoController controller;
    @Mock
    private EnderecoService service;
    @Mock
    private EnderecoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private EnderecoDTO dto;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ClienteDTO cliente = new ClienteDTO(id, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        dto = new EnderecoDTO(id, "Rua Guarani", 10, cliente);
        List<EnderecoDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);

        when(service.getById(anyLong())).thenReturn(dto);
        when(service.getByRua(anyString())).thenReturn(dto);
        when(service.getAll()).thenReturn(dtoList);
        when(service.create(any(EnderecoDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(EnderecoDTO.class))).thenReturn(dto);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<EnderecoDTO>> responseEntity = controller.getAll();

        List<EnderecoDTO> dtoList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(dtoList);
    }

    @Test
    void testGetById() {
        ResponseEntity<EnderecoDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetByRua() {
        String rua = "Rua Guarani";
        ResponseEntity<EnderecoDTO> response = controller.getByRua(rua);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testCreate() {
        ResponseEntity<EnderecoDTO> response = controller.create(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testUpdate() {
        ResponseEntity<EnderecoDTO> response = controller.update(id, dto);

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
