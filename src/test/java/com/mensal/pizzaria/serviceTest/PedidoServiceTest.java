package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.Forma_Pagamento;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.PedidoRepository;
import com.mensal.pizzaria.service.PedidoService;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class PedidoServiceTest {
    @InjectMocks
    private PedidoService service;

    @Mock
    private PedidoService serviceTest;

    @Mock
    private PedidoRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private PedidoEntity pedido;
    private PedidoDTO pedidoDTO;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();

        ProdutoEntity produto = new ProdutoEntity(1L, "Pizza Calabreza", 25.0, null);
        ClienteEntity cliente = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        PedidoEntity pedido = new PedidoEntity(1L, Collections.singletonList(produto), cliente, true, Forma_Pagamento.PIX, 25.0);

        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        pedidoDTO = new PedidoDTO(1L, Collections.singletonList(produtoDTO), clienteDTO, true, Forma_Pagamento.PIX, 25.0);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {
        List<PedidoEntity> listEntity = new ArrayList<>();
        listEntity.add(pedido);

        when(repository.findAll()).thenReturn(listEntity);

        List<PedidoDTO> resultList = service.findAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void createTestException() {
        pedidoDTO.setId(1L);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(pedidoDTO));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void updateTestException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, pedidoDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void calculoTotalTest() {
        PedidoDTO pedido = new PedidoDTO();
        ProdutoDTO produto1 = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);
        ProdutoDTO produto2 = new ProdutoDTO(2L, "Pizza Pepperoni", 30.0, null);

        List<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        pedido.setProdutoList(produtos);

        Double total = service.calculoTotal(pedido);

        double result = 30.0 + 25.0;
        Assertions.assertEquals(result, total, 0.01);
    }
}
