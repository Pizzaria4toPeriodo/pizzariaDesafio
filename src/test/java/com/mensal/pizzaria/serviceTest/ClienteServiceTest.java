package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import com.mensal.pizzaria.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {
    @InjectMocks
    private ClienteService service;
    @Mock
    private ClienteRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private final String cpf = "36126170601";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ClienteDTO clienteDTO = new ClienteDTO(1L, "Gustavo", cpf, null, "+55 45 99988-7766");

        ClienteEntity clienteEntity = new ClienteEntity(1L, "Gustavo", cpf, null, "+55 45 99988-7766");
        ClienteEntity clienteEntity2 = new ClienteEntity(2L, "Marcelo", "29056578049", null, "+55 45 95544-3322");

        List<ClienteEntity> entityList = Arrays.asList(clienteEntity, clienteEntity2);

        when(repository.findById(id)).thenReturn(Optional.of(clienteEntity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        when(modelMapper.map(clienteEntity, ClienteDTO.class)).thenReturn(new ClienteDTO());
    }

    @Test
    void testGetByIdExistente() {
        ClienteDTO clienteDTO = service.getById(id);

        assertNotNull(clienteDTO);
        assertEquals(id, clienteDTO.getId());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void testGetByIdNaoExistente() {
        assertThrows(EntityNotFoundException.class, () -> service.getById(idNaoExistente));

        verify(repository, times(1)).findById(idNaoExistente);
    }
}
