package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.ProdutoRepository;
import com.mensal.pizzaria.service.ProdutoService;
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

import static org.mockito.Mockito.when;

@SpringBootTest
class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoService serviceTest;

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private ProdutoEntity produto;
    private ProdutoDTO produtoDTO;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();

        produto = new ProdutoEntity(1L, "Pizza", 25.5, null);

        produtoDTO = new ProdutoDTO(1L, "Pizza", 25.5, null);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findALlTest() {
        List<ProdutoEntity> listEntity = new ArrayList<>();
        listEntity.add(produto);

        when(repository.findAll()).thenReturn(listEntity);

        List<ProdutoDTO> resultList = service.getAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void createTestException() {
        produtoDTO.setId(1L);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(produtoDTO));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void updateTestException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, produtoDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
