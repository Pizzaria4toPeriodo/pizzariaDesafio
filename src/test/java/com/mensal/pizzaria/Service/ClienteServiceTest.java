package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import com.mensal.pizzaria.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @InjectMocks
    private com.mensal.pizzaria.Service.ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCliente() {



        ClienteDTO clienteDTO = new ClienteDTO(1L,"Luis","80172015910",null,"45991539849");
        ClienteEntity clienteEntity= new ClienteEntity();



        when(modelMapper.map(clienteDTO, ClienteEntity.class)).thenReturn(clienteEntity);
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(modelMapper.map(clienteEntity, ClienteDTO.class)).thenReturn(clienteDTO);


        ClienteDTO result = clienteService.create(clienteDTO);

        Assertions.assertEquals(clienteDTO,result);
        verify(modelMapper, times(1)).map(clienteDTO, ClienteEntity.class);
        verify(clienteRepository, times(1)).save(clienteEntity);
    }
}
