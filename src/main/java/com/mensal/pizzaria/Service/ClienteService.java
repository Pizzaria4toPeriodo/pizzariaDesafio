package com.mensal.pizzaria.Service;


import com.mensal.pizzaria.DTO.ClienteDto;
import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {



    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll(){

        return clienteRepository.findAll();
    }


    public Cliente findByid(Long id){

        Optional<Cliente> clienteBD= clienteRepository.findById(id);

        return clienteBD.get();
    }

    @Transactional
    public Cliente cadastrar(Cliente cliente){

        Optional<Cliente> clienteBD = clienteRepository.findById(cliente.getId());
        Assert.isTrue(clienteBD.isEmpty(),"Cliente cadastrado com esse ID") ;

       return clienteRepository.save(cliente);

    }


    @Transactional
    public Cliente editar(Cliente cliente, Long id){

        Optional<Cliente> clienteBD = clienteRepository.findById(id);

        Assert.isTrue(!clienteBD.isEmpty(),"Cliente nao cadastrado com esse ID") ;



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
