package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public FuncionarioDTO create(FuncionarioDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class);
    }
}