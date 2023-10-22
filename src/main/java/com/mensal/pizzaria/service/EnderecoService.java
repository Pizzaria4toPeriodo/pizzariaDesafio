package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EnderecoEntity create(EnderecoEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public List<EnderecoEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public EnderecoEntity getById(Long id) {
        Optional<EnderecoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Endereço não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public EnderecoEntity getByRua(String rua) {
        return repository.findByRua(rua);
    }

    @Transactional
    public List<EnderecoEntity> getEnderecosByNomeCliente(String nome) {
        return repository.findEnderecosByNomeCliente(nome);
    }

    @Transactional
    public EnderecoEntity update(Long id, EnderecoEntity entity) {
        EnderecoEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}