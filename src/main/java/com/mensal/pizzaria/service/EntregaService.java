package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.EntregaDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.entity.EntregaEntity;
import com.mensal.pizzaria.repository.EntregaRepository;
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
public class EntregaService {
    @Autowired
    private EntregaRepository repository;
    @Autowired
    private ModelMapper modelMapper;




    public List<EntregaDTO> findAll(){

        List<EntregaEntity> entragasEntity = this.repository.findAll();
        List<EntregaDTO> entregasDto = new ArrayList<>();

        for (EntregaEntity entrega:entragasEntity) {

           EntregaDTO  entregaDTO = modelMapper.map(entrega,EntregaDTO.class);
            entregasDto.add(entregaDTO);

        }

        return entregasDto;


    }



    public  EntregaDTO findById(Long id){

        Optional<EntregaEntity> entregaBD = this.repository.findById(id);

        Assert.isTrue(entregaBD.isPresent(),"Entrega nao cadastrada");

        EntregaDTO entregaDTO = modelMapper.map(entregaBD.get(),EntregaDTO.class);

        return entregaDTO;

    }

    @Transactional
    public EntregaDTO create(EntregaDTO dto) {

        EntregaEntity entregaEntity = modelMapper.map(dto,EntregaEntity.class);
        EntregaEntity entregaSaved = repository.save(entregaEntity);
        EntregaDTO entregaDTO = modelMapper.map(entregaSaved,EntregaDTO.class);
        return entregaDTO;
    }

    @Transactional
    public EntregaDTO update(Long id, EntregaDTO dto) {
        EntregaEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), EntregaDTO.class);
    }
}