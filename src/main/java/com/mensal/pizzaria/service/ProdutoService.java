package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ProdutoEntity create(ProdutoEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public List<ProdutoEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public ProdutoEntity getById(Long id) {
        Optional<ProdutoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Produto não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public ProdutoEntity getByNomeProduto(String nome) {
        return repository.findByNomeProduto(nome);
    }

    @Transactional
    public ProdutoEntity update(Long id, ProdutoEntity entity) {
        ProdutoEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}