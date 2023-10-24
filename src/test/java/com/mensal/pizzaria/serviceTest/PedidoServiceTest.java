package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import com.mensal.pizzaria.repository.PedidoRepository;
import com.mensal.pizzaria.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PedidoServiceTest {
    @InjectMocks
    private PedidoService service;
    @Mock
    private PedidoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private PedidoEntity entity;
    private PedidoEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();

        PedidoDTO dto = new PedidoDTO();
        dto.setId(id);

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setPreco(5.0);
        List<ProdutoEntity> produtoEntityList = new ArrayList<>();
        produtoEntityList.add(produtoEntity);

        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setCategoria(Categoria.TRADICIONAL);
        pizzaEntity.setTamanho(Tamanho.M);
        List<PizzaEntity> pizzaEntityList = new ArrayList<>();
        pizzaEntityList.add(pizzaEntity);

        entity = new PedidoEntity();
        entity.setId(id);
        entity.setProdutoList(produtoEntityList);
        entity.setPizzaList(pizzaEntityList);

        PedidoEntity entity2 = new PedidoEntity();
        entity2.setId(2L);

        List<PedidoEntity> entityList = Arrays.asList(entity, entity2);

        updatedEntity = new PedidoEntity();
        updatedEntity.setId(id);
        updatedEntity.setProdutoList(produtoEntityList);
        updatedEntity.setPizzaList(pizzaEntityList);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        PedidoEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testGetByIdExistente() {
        PedidoEntity database = service.getById(id);

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
    void testFindAll() {
        List<PedidoEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        PedidoEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
