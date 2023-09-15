package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.EntregaDTO;
import com.mensal.pizzaria.entity.EntregaEntity;
import com.mensal.pizzaria.repository.EntregaRepository;
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
        repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, EntregaEntity.class)), EntregaDTO.class);
    }
}