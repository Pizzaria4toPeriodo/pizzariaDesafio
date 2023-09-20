package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import com.mensal.pizzaria.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {
    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteService serviceTest;

    @Mock
    private ClienteRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private ClienteEntity cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setup(){
        modelMapper = new ModelMapper();

        cliente = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        clienteDTO = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<ClienteEntity> listEntity = new ArrayList<>();
        listEntity.add(cliente);

        when(repository.findAll()).thenReturn(listEntity);

        List<ClienteDTO> resultList = service.findAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void testCreateException() {
        clienteDTO.setId(1L);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(clienteDTO));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, clienteDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
