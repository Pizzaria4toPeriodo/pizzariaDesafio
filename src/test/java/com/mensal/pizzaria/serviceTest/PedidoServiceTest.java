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
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        PedidoDTO pedidoDTO = new PedidoDTO(1L, Collections.singletonList(produtoDTO), clienteDTO, true, Forma_Pagamento.PIX, 25.0);

        ProdutoEntity produtoEntity = new ProdutoEntity(1L, "Pizza Calabreza", 25.0, null);
        ClienteEntity clienteEntity = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");

        PedidoEntity pedidoEntity = new PedidoEntity(1L, Collections.singletonList(produtoEntity), clienteEntity, true, Forma_Pagamento.PIX, 25.0);
        PedidoEntity pedidoEntity2 = new PedidoEntity(1L, Collections.singletonList(produtoEntity), clienteEntity, true, Forma_Pagamento.PIX, 25.0);

        List<PedidoEntity> entityList = Arrays.asList(pedidoEntity, pedidoEntity2);

        when(repository.findById(id)).thenReturn(Optional.of(pedidoEntity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        when(modelMapper.map(pedidoEntity, PedidoDTO.class)).thenReturn(pedidoDTO);
    }

    @Test
    void testGetByIdExistente() {
        PedidoDTO pedidoDTOBanco = service.getById(id);

        assertNotNull(pedidoDTOBanco);
        assertEquals(id, pedidoDTOBanco.getId());

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

    @Test
    void calculoTotalTest() {
        PedidoDTO pedido = new PedidoDTO();
        ProdutoDTO produto1 = new ProdutoDTO(1L, "Pizza Calabreza", 25.0, null);
        ProdutoDTO produto2 = new ProdutoDTO(2L, "Pizza Pepperoni", 30.0, null);

        List<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        pedido.setProdutoList(produtos);

        //Double total = service.calculoTotal(pedido);

        double result = 30.0 + 25.0;
        assertEquals(result, total, 0.01);
    }
}
