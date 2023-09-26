package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import com.mensal.pizzaria.service.EnderecoService;
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
class EnderecoServiceTest {
    @InjectMocks
    private EnderecoService service;
    @Mock
    private EnderecoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ClienteDTO clienteDTO = new ClienteDTO(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        EnderecoDTO enderecoDTO = new EnderecoDTO(1L, "Rua Guarani", 11, clienteDTO);

        ClienteEntity clienteEntity = new ClienteEntity(1L, "Gustavo", "36126170601", null, "+55 45 99988-7766");
        EnderecoEntity enderecoEntity = new EnderecoEntity(1L, "Rua Guarani", 11, clienteEntity);
        EnderecoEntity enderecoEntity2 = new EnderecoEntity(2L, "Rua Taquara", 12, clienteEntity);

        List<EnderecoEntity> entityList = Arrays.asList(enderecoEntity, enderecoEntity2);

        when(repository.findById(id)).thenReturn(Optional.of(enderecoEntity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        when(modelMapper.map(enderecoEntity, EnderecoDTO.class)).thenReturn(enderecoDTO);
    }

    @Test
    void testGetByIdExistente() {
        EnderecoDTO enderecoDTOBanco = service.getById(id);

        assertNotNull(enderecoDTOBanco);
        assertEquals(id, enderecoDTOBanco.getId());

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
