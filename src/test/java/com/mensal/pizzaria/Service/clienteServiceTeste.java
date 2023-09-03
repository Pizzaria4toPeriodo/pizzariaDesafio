package com.mensal.pizzaria.Service;


import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Entity.Endereco;
import com.mensal.pizzaria.Repository.ClienteRepository;
import com.mensal.pizzaria.Service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class clienteServiceTeste {

 @InjectMocks
 ClienteService clienteService;

 @Mock
 ClienteRepository clienteRepository;
 Cliente cliente;
 Endereco endereco = new Endereco(1L,"lobos",222,cliente);
public  List<Endereco>  id_endereco = Arrays.asList(endereco);




    @BeforeEach
 public void criarCliente(){
     cliente = new Cliente(1L,"Luis Rodriguez","801.720.159-10",id_endereco,"45991539849");
 }

    @Test
    public void cadastrarUsuarioTeste() {

        when(clienteService.cadastrar(cliente)).thenReturn(cliente);
        
        Assertions.assertEquals(clienteService.cadastrar(cliente),cliente);


    }



}
