package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public FuncionarioEntity create(FuncionarioEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public List<FuncionarioEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public FuncionarioEntity getById(Long id) {
        Optional<FuncionarioEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Funcionário não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public FuncionarioEntity getByNomeFuncionario(String nome) {
        return repository.findByNomeFuncionario(nome);
    }

    @Transactional
    public FuncionarioEntity update(Long id, FuncionarioEntity entity) {
        FuncionarioEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}