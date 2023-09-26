package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ClienteDTO getById(Long id) {
        Optional<ClienteEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            ClienteEntity entity = optional.get();
            return modelMapper.map(entity, ClienteDTO.class);
        } else {
            throw new EntityNotFoundException("Cliente não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public ClienteDTO getByCpf(String cpf) {
        return modelMapper.map(repository.findByCpf(cpf), ClienteDTO.class);
    }

    @Transactional
    public List<ClienteDTO> getAll() {
        List<ClienteDTO> list = new ArrayList<>();
        for (ClienteEntity entity : repository.findAll()) {
            ClienteDTO map = modelMapper.map(entity, ClienteDTO.class);
            list.add(map);
        }

        return list;
    }

    @Transactional
    public ClienteDTO create(ClienteDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, ClienteEntity.class)), ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        ClienteEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), ClienteDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}