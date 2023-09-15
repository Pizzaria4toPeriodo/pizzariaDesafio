package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.EntregaDTO;
import com.mensal.pizzaria.Entity.EntregaEntity;
import com.mensal.pizzaria.Repository.EntregaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<EntregaDTO> findAll() {
        return repository.findAll().stream().map(entity -> modelMapper.map(entity, EntregaDTO.class)).collect(Collectors.toList());
    }

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