package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EnderecoDTO create(EnderecoDTO dto) {
        EnderecoEntity enderecoEntity = modelMapper.map(dto,EnderecoEntity.class);
        Optional<EnderecoEntity> enderecoBD = this.repository.findById(enderecoEntity.getId());

        EnderecoEntity savedEntity = repository.save(enderecoEntity);

        Assert.isTrue(enderecoBD.get().getId() != enderecoEntity.getId(),"Id do Cliente já existe");
        Assert.isTrue(enderecoBD.get().getRua().matches("[a-zA-Z\\s]+"),"Somente é permitido letras no nome da rua");
        Assert.isTrue(String.valueOf(enderecoBD.get().getNumero()).matches("\\d+"), "Somente é permitido numeros no campo do número");


        return modelMapper.map(savedEntity, EnderecoDTO.class);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        EnderecoEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), EnderecoDTO.class);
    }
}