package com.mensal.pizzaria.controllerTest;

import com.mensal.pizzaria.controller.FuncionarioController;
import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import com.mensal.pizzaria.service.FuncionarioService;
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
class FuncionarioControllerTest {
    @InjectMocks
    private FuncionarioController controller;
    @Mock
    private FuncionarioService service;
    @Mock
    private FuncionarioRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private FuncionarioDTO dto;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new FuncionarioDTO(id, "Gustavo", "Cozinheiro");
        List<FuncionarioDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);

        when(service.getById(anyLong())).thenReturn(dto);
        when(service.getByNomeFuncionario(anyString())).thenReturn(dto);
        when(service.getAll()).thenReturn(dtoList);
        when(service.create(any(FuncionarioDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(FuncionarioDTO.class))).thenReturn(dto);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<FuncionarioDTO>> responseEntity = controller.getAll();

        List<FuncionarioDTO> dtoList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(dtoList);
    }

    @Test
    void testGetById() {
        ResponseEntity<FuncionarioDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetByRua() {
        String nomeFuncionario = "Gustavo";
        ResponseEntity<FuncionarioDTO> response = controller.getByNomeFuncionario(nomeFuncionario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testCreate() {
        ResponseEntity<FuncionarioDTO> response = controller.create(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testUpdate() {
        ResponseEntity<FuncionarioDTO> response = controller.update(id, dto);

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
