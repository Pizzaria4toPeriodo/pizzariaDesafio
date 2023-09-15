package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.ClienteDTO;
import com.mensal.pizzaria.Entity.ClienteEntity;
import com.mensal.pizzaria.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ClienteDTO findByNomeCliente(String nome) {
        return modelMapper.map(repository.findByNomeCliente(nome), ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO findByCpf(String cpf) {
        return modelMapper.map(repository.findByCpf(cpf), ClienteDTO.class);
    }

    @Transactional
    public List<ClienteDTO> findAll() {
        return repository.findAll().stream().map(entity -> modelMapper.map(entity, ClienteDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public ClienteDTO create(ClienteDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, ClienteEntity.class)), ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, ClienteEntity.class)), ClienteDTO.class);
    }
}