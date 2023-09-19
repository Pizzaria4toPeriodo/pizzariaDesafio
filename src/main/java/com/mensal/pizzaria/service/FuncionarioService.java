package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<FuncionarioDTO> findAll() {
        List<FuncionarioDTO> list = new ArrayList<>();
        for (FuncionarioEntity entity : repository.findAll()) {
            FuncionarioDTO map = modelMapper.map(entity, FuncionarioDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public FuncionarioDTO create(FuncionarioDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        FuncionarioEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), FuncionarioDTO.class);
    }
}