package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.PizzaRepository;
import com.mensal.pizzaria.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public PizzaEntity create(PizzaEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public PizzaEntity getById(Long id) {
        Optional<PizzaEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Pizza não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public PizzaEntity getByNomePizza(String nome) {
        return repository.findByNomePizza(nome);
    }

    @Transactional
    public List<PizzaEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public PizzaEntity update(Long id, PizzaEntity entity) {
        PizzaEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza não encontrada com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
