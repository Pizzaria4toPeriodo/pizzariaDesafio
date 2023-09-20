package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.entity.ClienteEntity;
import com.mensal.pizzaria.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    public List<ClienteDTO> findAll() {
        List<ClienteDTO> list = new ArrayList<>();
        for (ClienteEntity entity : repository.findAll()) {
            ClienteDTO map = modelMapper.map(entity, ClienteDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public ClienteDTO findById(Long id) {
        Optional<ClienteEntity> clienteBD = repository.findById(id);

        return modelMapper.map(clienteBD,ClienteDTO.class);

    }



    @Transactional
    public ClienteDTO create(ClienteDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, ClienteEntity.class)), ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        ClienteEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), ClienteDTO.class);
    }

    @Transactional
    public void delete(Long id){
        ClienteEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

         repository.deleteById(existingEntity.getId());

    }


}