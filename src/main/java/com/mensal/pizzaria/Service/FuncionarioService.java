package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.FuncionarioDTO;
import com.mensal.pizzaria.Entity.FuncionarioEntity;
import com.mensal.pizzaria.Repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findAll().stream().map(entity -> modelMapper.map(entity, FuncionarioDTO.class)).collect(Collectors.toList());
    }

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