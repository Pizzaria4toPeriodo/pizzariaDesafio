package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.ProdutoRepository;
import com.mensal.pizzaria.service.ProdutoService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class PizzaServiceTest {
    @InjectMocks
    private ProdutoService service;
    @Mock
    private ProdutoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private ProdutoEntity entity;
    private ProdutoEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();

        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(id);
        dto.setNomeProduto("Pizza");

        entity = new ProdutoEntity();
        entity.setId(id);
        entity.setNomeProduto("Pizza");

        ProdutoEntity entity2 = new ProdutoEntity();
        entity2.setId(2L);
        entity.setNomeProduto("Pizza");

        List<ProdutoEntity> entityList = Arrays.asList(entity, entity2);

        updatedEntity = new ProdutoEntity();
        updatedEntity.setId(id);
        updatedEntity.setNomeProduto("Coca-cola");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByNomeProduto("Pizza")).thenReturn(entity);
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        ProdutoEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("Pizza", createdEntity.getNomeProduto());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testGetByIdExistente() {
        ProdutoEntity database = service.getById(id);

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
    void testGetByNomeProduto() {
        ProdutoEntity database = service.getByNomeProduto("Pizza");

        assertEquals("Pizza", database.getNomeProduto());
    }

    @Test
    void testFindAll() {
        List<ProdutoEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        ProdutoEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Coca-cola", result.getNomeProduto());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
