package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public FuncionarioDTO findByNomeFuncionario(String nome) {
        return modelMapper.map(repository.findByNomeFuncionario(nome), FuncionarioDTO.class);
    }

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
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id do funcionário não deve ser inserido");
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        FuncionarioEntity existingEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), FuncionarioDTO.class);
    }
}