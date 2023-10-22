package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import com.mensal.pizzaria.service.FuncionarioService;
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

@
@SpringBootTest
class FuncionarioServiceTest {
    @InjectMocks
    private FuncionarioService service;

    @Mock
    private FuncionarioService serviceTest;

    @Mock
    private FuncionarioRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private FuncionarioEntity funcionario;
    private FuncionarioDTO funcionarioDTO;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();

        funcionario = new FuncionarioEntity();

        funcionarioDTO = new FuncionarioDTO(1L, "Gustavo", "Entregador");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {
        List<FuncionarioEntity> listEntity = new ArrayList<>();
        listEntity.add(funcionario);

        when(repository.findAll()).thenReturn(listEntity);

        List<FuncionarioDTO> resultList = service.findAll();

        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void testCreateException() {
        funcionarioDTO.setId(1L);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(funcionarioDTO));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, funcionarioDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
