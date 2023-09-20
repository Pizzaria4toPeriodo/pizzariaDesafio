package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import com.mensal.pizzaria.service.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EnderecoServiceTest {
    @InjectMocks
    private EnderecoService service;

    @Mock
    private EnderecoService serviceTest;

    @Mock
    private EnderecoRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private EnderecoEntity endereco;
    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setup(){
        modelMapper = new ModelMapper();

        ClienteEntity cliente = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        endereco = new EnderecoEntity(1L, "Rua Guarani", 11, cliente);

        ClienteDTO clienteDTO = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        enderecoDTO = new EnderecoDTO(1L, "Rua Guarani", 11, clienteDTO);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {
        List<EnderecoEntity> listEntity = new ArrayList<>();
        listEntity.add(endereco);

        when(repository.findAll()).thenReturn(listEntity);

        List<EnderecoDTO> resultList = service.findAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void createTestException() {
        enderecoDTO.setId(1L);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(enderecoDTO));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void updateTestException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, enderecoDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}