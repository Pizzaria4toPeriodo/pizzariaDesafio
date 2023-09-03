package com.mensal.pizzaria;


import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Repository.ClienteRepository;
import com.mensal.pizzaria.Service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBeans;

@ExtendWith(MockitoExtension.class)
public class clienteServiceTeste {

 @InjectMocks
 private ClienteRepository clienteRepository;

    @Test
    public void cadastrarUsuarioTeste() {
        Cliente cliente = new Cliente();


        Assertions.assertEquals(clienteService.cadastrar(cliente),cliente);


    }



}
