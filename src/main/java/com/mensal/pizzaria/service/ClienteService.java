package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ClienteEntity create(ClienteEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public List<ClienteEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public ClienteEntity getById(Long id) {
        Optional<ClienteEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Cliente não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public ClienteEntity getByNomeCliente(String nome) {
        Optional<ClienteEntity> optional = repository.findByNomeCliente(nome);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Cliente não encontrada com o Nome: " + nome);
        }
    }

    @Transactional
    public ClienteEntity getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Transactional
    public ClienteEntity update(Long id, ClienteEntity entity) {
        ClienteEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}