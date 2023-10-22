package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import com.mensal.pizzaria.service.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {
    @InjectMocks
    private FuncionarioService service;
    @Mock
    private FuncionarioRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private FuncionarioEntity entity;
    private FuncionarioEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();

        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(id);
        dto.setNomeFuncionario("Marcelo");

        entity = new FuncionarioEntity();
        entity.setId(id);
        entity.setNomeFuncionario("Marcelo");

        FuncionarioEntity entity2 = new FuncionarioEntity();
        entity2.setId(2L);
        entity.setNomeFuncionario("Marcelo");

        List<FuncionarioEntity> entityList = Arrays.asList(entity, entity2);

        updatedEntity = new FuncionarioEntity();
        updatedEntity.setId(id);
        updatedEntity.setNomeFuncionario("Zé");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByNomeFuncionario("Marcelo")).thenReturn(entity);
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        FuncionarioEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("Marcelo", createdEntity.getNomeFuncionario());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testGetByIdExistente() {
        FuncionarioEntity database = service.getById(id);

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
    void testGetByNomeFuncionario() {
        FuncionarioEntity database = service.getByNomeFuncionario("Marcelo");

        assertEquals("Marcelo", database.getNomeFuncionario());
    }

    @Test
    void testFindAll() {
        List<FuncionarioEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        FuncionarioEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Zé", result.getNomeFuncionario());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
