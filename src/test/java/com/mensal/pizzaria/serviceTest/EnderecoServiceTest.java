package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import com.mensal.pizzaria.service.EnderecoService;
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
class EnderecoServiceTest {
    @InjectMocks
    private EnderecoService service;
    @Mock
    private EnderecoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private EnderecoEntity entity;
    private EnderecoEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();

        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(id);
        dto.setRua("Guarani");

        entity = new EnderecoEntity();
        entity.setId(id);
        entity.setRua("Guarani");

        EnderecoEntity entity2 = new EnderecoEntity();
        entity2.setId(2L);
        entity2.setRua("Guarani");

        List<EnderecoEntity> entityList = Arrays.asList(entity, entity2);

        updatedEntity = new EnderecoEntity();
        updatedEntity.setId(id);
        updatedEntity.setRua("Taquara");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByRua("Guarani")).thenReturn(entity);
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testGetByIdExistente() {
        EnderecoEntity database = service.getById(id);

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
    void testGetByRua() {
        EnderecoEntity database = service.getByRua("Guarani");

        assertEquals("Guarani", database.getRua());
    }

    @Test
    void testFindAll() {
        List<EnderecoEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        EnderecoEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("Guarani", createdEntity.getRua());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        EnderecoEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Taquara", result.getRua());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
