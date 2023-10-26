package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import com.mensal.pizzaria.repository.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public void calculoPreco(PizzaEntity entity) {
        double precoCategoria = switch (entity.getCategoria()) {
            case TRADICIONAL -> 10.0;
            case ESPECIAL -> 20.0;
            case DOCE -> 15.0;
        };

        double precoTamanho = switch (entity.getTamanho()) {
            case P -> 20.0;
            case M -> 30.0;
            case G -> 40.0;
            case GG -> 50.0;
        };

        entity.setPreco(precoCategoria + precoTamanho);
    }

    private void qntSaboresPorTamanho(PizzaEntity entity){
        int qntSabores = 0;
        switch (entity.getTamanho()) {
            case P -> qntSabores = 2;
            case M, G -> qntSabores = 3;
            case GG -> qntSabores = 4;
        }
        if (entity.getSaborList().size()>qntSabores){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de sabores excedida");
        }
    }

    @Transactional
    public PizzaEntity create(PizzaEntity entity) {
        qntSaboresPorTamanho(entity);
        calculoPreco(entity);

        return repository.save(entity);
    }

    @Transactional
    public List<PizzaEntity> getAll() {
        return repository.findAll();
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
    public PizzaEntity getByTamanho(Tamanho tamanho) {
        return repository.findByTamanho(tamanho);
    }

    @Transactional
    public PizzaEntity getByCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    @Transactional
    public PizzaEntity update(Long id, PizzaEntity entity) {
        PizzaEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pizza não encontrada com o ID: " + id));

        calculoPreco(entity);

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
