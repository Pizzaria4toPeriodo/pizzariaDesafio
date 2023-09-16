package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.EntregaDTO;
import com.mensal.pizzaria.entity.EntregaEntity;
import com.mensal.pizzaria.repository.EntregaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EntregaDTO create(EntregaDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, EntregaEntity.class)), EntregaDTO.class);
    }

    @Transactional
    public EntregaDTO update(Long id, EntregaDTO dto) {
        EntregaEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), EntregaDTO.class);
    }
}