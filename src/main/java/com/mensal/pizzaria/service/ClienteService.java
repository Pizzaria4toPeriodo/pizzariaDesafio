package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;



    public List<ClienteDTO> findAll(){

         List<ClienteEntity> clientesEntity = this.repository.findAll();
         List<ClienteDTO>  clientesDTO = new ArrayList<>();

        for (ClienteEntity cliente:clientesEntity) {
            ClienteDTO clienteDTO = modelMapper.map(cliente,ClienteDTO.class);
            clientesDTO.add(clienteDTO);
        }

        return clientesDTO;
    }

    public ClienteDTO findById(Long id){

        Optional<ClienteEntity> clienteOptional = this.repository.findById(id);

        Assert.isTrue(clienteOptional.isPresent(),"Cliente nao cadastrado");

        return modelMapper.map(clienteOptional.get(),ClienteDTO.class);


    }

    public ClienteDTO findByNome(String nome){

        Optional<ClienteEntity> clienteBD = this.repository.findByNomeCliente(nome);
        Assert.isTrue(clienteBD.isPresent(),"Cliente nao cadastrado com esse nome");

        return modelMapper.map(clienteBD,ClienteDTO.class);

    }



    @Transactional
    public ClienteDTO create(ClienteDTO dto) {

            ClienteEntity clienteEntity = modelMapper.map(dto,ClienteEntity.class);
            Optional<ClienteEntity> clienteBD = this.repository.findById(clienteEntity.getId());



       Assert.isTrue(clienteBD.get().getId() != clienteEntity.getId(),"Id do Cliente já existe");
       Assert.isTrue(clienteBD.get().getCpf() != clienteEntity.getCpf(),"CPF do Cliente já cadastrado");
      Assert.isTrue(clienteBD.get().getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"),"Formato de CPF invalido, deve ser: 000.000.000-00");
     Assert.isTrue(clienteBD.get().getTelefone().matches("\\(\\d{2}\\)\\d{5}-\\d{4}"),"Formato de TELEFONE invalido, deve ser: (00)0000-0000");
    Assert.isTrue(clienteBD.get().getNomeCliente().matches("[a-zA-Z\\s]+"),"Somente é permitido letras no nome");

        ClienteEntity savedEntity = repository.save(clienteEntity);

        return modelMapper.map(savedEntity, ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        ClienteEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), ClienteDTO.class);
    }
}