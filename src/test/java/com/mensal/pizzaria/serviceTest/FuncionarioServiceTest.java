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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(1L, "Gustavo", "Entregador");

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity(1L, "Gustavo", "Entregador");
        FuncionarioEntity funcionarioEntity2 = new FuncionarioEntity(2L, "Marcelo", "Caixa");

        List<FuncionarioEntity> entityList = Arrays.asList(funcionarioEntity, funcionarioEntity2);

        when(repository.findById(id)).thenReturn(Optional.of(funcionarioEntity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        when(modelMapper.map(funcionarioEntity, FuncionarioDTO.class)).thenReturn(funcionarioDTO);
    }

    @Test
    void testGetByIdExistente() {
        FuncionarioDTO funcionarioDTOBanco = service.getById(id);

        assertNotNull(funcionarioDTOBanco);
        assertEquals(id, funcionarioDTOBanco.getId());

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
