package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll(){return clienteRepository.findAll();
    }

    public Cliente findByid(Long id){
        Optional<Cliente> clienteBD= clienteRepository.findById(id);
        return clienteBD.get();
    }

    @Transactional
    public Cliente cadastrar(Cliente cliente){
        Assert.isNull(clienteRepository.findByCpf(cliente.getCpf()),"Cliente cadastrado com ese cpf");
        Assert.isTrue(cliente.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"),"Formato de CPF invalido, deve ser: 000.000.000-00");
        Assert.isTrue( cliente.getNome().length() > 2,"Quantidade de caracteres minimos para ó nome é 3");
        Assert.isTrue( cliente.getNome().length() <= 15,"Quantidade de caracteres Maximos para ó nome é 15");
        Assert.isTrue(cliente.getNome().matches("[a-zA-Z\\s]+"),"Somente é permitido letras no nome");


        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente editar(Cliente cliente, Long id){
        Optional<Cliente> clienteBD = clienteRepository.findById(id);
        Assert.isTrue(!clienteBD.isEmpty(),"Cliente nao cadastrado com esse ID") ;
        Assert.isNull(clienteRepository.findByCpf(cliente.getCpf()),"Cliente cadastrado com ese cpf");
        Assert.isTrue(cliente.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"),"Formato de CPF invalido, deve ser: 000.000.000-00");
        Assert.isTrue( cliente.getNome().length() > 2,"Quantidade de caracteres minimos para ó nome é 3");
        Assert.isTrue( cliente.getNome().length() <= 15,"Quantidade de caracteres Maximos para ó nome é 15");
        Assert.isTrue(cliente.getNome().matches("[a-zA-Z\\s]+"),"Somente é permitido letras no nome");

        return clienteRepository.save(cliente);
    }

    @Transactional
    public String excluir(Long id){

        Optional<Cliente> clienteBD = clienteRepository.findById(id);

        Assert.isTrue(!clienteBD.isEmpty(),"Cliente nao cadastrado com esse ID") ;

        clienteRepository.deleteById(id);
        return  "cliente excluido";
    }
}
