package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.SaborDTO;
import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.repository.SaborRepository;
import com.mensal.pizzaria.service.SaborService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SaborServiceTest {
    @InjectMocks
    private SaborService service;
    @Mock
    private SaborRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private SaborEntity entity;
    private SaborEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();

        SaborDTO dto = new SaborDTO();
        dto.setId(id);
        dto.setNomeSabor("Calabresa");
        dto.setCategoria(Categoria.TRADICIONAL);

        entity = new SaborEntity();
        entity.setId(id);
        entity.setNomeSabor("Calabresa");

        SaborEntity entity2 = new SaborEntity();
        entity2.setId(2L);
        entity.setNomeSabor("Calabresa");

        List<SaborEntity> entityList = Arrays.asList(entity, entity2);

        updatedEntity = new SaborEntity();
        updatedEntity.setId(id);
        updatedEntity.setNomeSabor("Portuguesa");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByNomeSabor("Calabresa")).thenReturn(entity);
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        SaborEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("Calabresa", createdEntity.getNomeSabor());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testGetByIdExistente() {
        SaborEntity database = service.getById(id);

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
    void testGetByNomeSabor() {
        SaborEntity database = service.getByNomeSabor("Calabresa");

        assertEquals("Calabresa", database.getNomeSabor());
    }

    @Test
    void testFindAll() {
        List<SaborEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        SaborEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Portuguesa", result.getNomeSabor());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
