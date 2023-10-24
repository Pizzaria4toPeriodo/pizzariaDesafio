package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.repository.SaborRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public SaborEntity create(SaborEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public List<SaborEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public SaborEntity getById(Long id) {
        Optional<SaborEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Produto não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public SaborEntity getByNomeSabor(String nome) {
        return repository.findByNomeSabor(nome);
    }

    @Transactional
    public SaborEntity getByCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    @Transactional
    public SaborEntity update(Long id, SaborEntity entity) {
        SaborEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
