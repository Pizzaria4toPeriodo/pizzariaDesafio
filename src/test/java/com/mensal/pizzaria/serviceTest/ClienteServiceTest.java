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
    private ClienteEntity entity;
    private ClienteEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();

        ClienteDTO dto = new ClienteDTO();
        dto.setId(id);
        dto.setNomeCliente("Gustavo");
        dto.setCpf("47917474534");

        entity = new ClienteEntity();
        entity.setId(id);
        entity.setNomeCliente("Gustavo");
        entity.setCpf("47917474534");

        ClienteEntity entity2 = new ClienteEntity();
        entity2.setId(2L);
        entity2.setCpf("47917474534");

        List<ClienteEntity> entityList = Arrays.asList(entity, entity2);

        updatedEntity = new ClienteEntity();
        updatedEntity.setId(id);
        updatedEntity.setCpf("60488595037");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByCpf("47917474534")).thenReturn(entity);
        when(repository.findByNomeCliente("Gustavo")).thenReturn(Optional.of(entity));
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        ClienteEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("47917474534", createdEntity.getCpf());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testFindAll() {
        List<ClienteEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByIdExistente() {
        ClienteEntity database = service.getById(id);

        assertNotNull(database);
        assertEquals(id, database.getId());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void testGetByIdNaoExistente() {
        assertThrows(EntityNotFoundException.class, () -> service.getById(idNaoExistente));

        verify(repository, times(1)).findById(idNaoExistente);
    }

    @Test
    void testGetByCPF() {
        ClienteEntity database = service.getByCpf("47917474534");

        assertEquals("47917474534", database.getCpf());

        verify(repository, times(1)).findByCpf("47917474534");
    }

    @Test
    void testGetByNomeCliente() {
        ClienteEntity database = service.getByNomeCliente("Gustavo");

        assertEquals("Gustavo", database.getNomeCliente());

        verify(repository, times(1)).findByNomeCliente("Gustavo");
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        ClienteEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("60488595037", result.getCpf());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
