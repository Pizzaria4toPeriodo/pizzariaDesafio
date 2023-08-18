package com.mensal.pizzaria.Service;


import com.mensal.pizzaria.DTO.ClienteDto;
import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Entity.Entrega;
import com.mensal.pizzaria.Repository.ClienteRepository;
import com.mensal.pizzaria.Repository.EntregaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {



    @Autowired
    EntregaRepository entregaRepository;

    public List<Entrega> findAll(){

        return entregaRepository.findAll();
    }


    public Entrega findByid(Long id){

        Optional<Entrega> entregaBD= entregaRepository.findById(id);

        return entregaBD.get();
    }

    @Transactional
    public Entrega cadastrar(Entrega entrega){

        Optional<Entrega> entregaBD = entregaRepository.findById(entrega.getId());
        Assert.isTrue(entregaBD.isEmpty(),"Entrega cadastrado com esse ID") ;

        return entregaRepository.save(entrega);

    }


    @Transactional
    public Entrega editar(Entrega entrega, Long id){

        Optional<Entrega> entregaBD = entregaRepository.findById(id);

        Assert.isTrue(!entregaBD.isEmpty(),"Entrega nao cadastrado com esse ID") ;



        return entregaRepository.save(entrega);

    }



    @Transactional
    public String excluir(Long id){

        Optional<Entrega> entregaBD = entregaRepository.findById(id);

        Assert.isTrue(!entregaBD.isEmpty(),"Entrega nao cadastrado com esse ID") ;

        entregaRepository.deleteById(id);
        return  "Entrega excluido";


    }





}
