package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
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
import static org.mockito.Mockito.*;

@SpringBootTest
class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService service;
    @Mock
    private ProdutoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);

        ProdutoEntity produtoEntity = new ProdutoEntity(1L, "Pizza Calabreza", 25.0, null);
        ProdutoEntity produtoEntity2 = new ProdutoEntity(2L, "Pizza Pepperoni", 27.0, null);

        List<ProdutoEntity> entityList = Arrays.asList(produtoEntity, produtoEntity2);

        when(repository.findById(id)).thenReturn(Optional.of(produtoEntity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        when(modelMapper.map(produtoEntity, ProdutoDTO.class)).thenReturn(produtoDTO);
    }

    @Test
    void testGetByIdExistente() {
        ProdutoDTO produtoDTOBanco = service.getById(id);

        assertNotNull(produtoDTOBanco);
        assertEquals(id, produtoDTOBanco.getId());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void testGetByIdNaoExistente() {
        assertThrows(EntityNotFoundException.class, () -> service.getById(idNaoExistente));

        verify(repository, times(1)).findById(idNaoExistente);
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
