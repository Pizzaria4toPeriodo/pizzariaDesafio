package com.mensal.pizzaria.Service;


import com.mensal.pizzaria.Entity.ClienteEntity;
import com.mensal.pizzaria.Entity.EnderecoEntity;
import com.mensal.pizzaria.Repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class clienteServiceTeste {

 @InjectMocks
 ClienteService clienteService;

 @Mock
 ClienteRepository clienteRepository;
 ClienteEntity clienteEntity;
 EnderecoEntity enderecoEntity = new EnderecoEntity(1L,"lobos",222, clienteEntity);
public  List<EnderecoEntity> id_enderecoEntity = Arrays.asList(enderecoEntity);




    @BeforeEach
 public void criarCliente(){
     clienteEntity = new ClienteEntity(1L,"Luis Rodriguez","801.720.159-10", id_enderecoEntity,"45991539849");
 }

    @Test
    public void cadastrarUsuarioTeste() {

        when(clienteService.cadastrar(clienteEntity)).thenReturn(clienteEntity);
        
        Assertions.assertEquals(clienteService.cadastrar(clienteEntity), clienteEntity);


    }



}
