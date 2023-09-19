package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<EnderecoEntity> findByRua(String rua) {
        return repository.findByRua(rua);
    }

    @Transactional
    public List<EnderecoDTO> findAll() {
        List<EnderecoDTO> list = new ArrayList<>();
        for (EnderecoEntity entity : repository.findAll()) {
            EnderecoDTO map = modelMapper.map(entity, EnderecoDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public EnderecoDTO create(EnderecoDTO dto) {
        EnderecoEntity enderecoEntity = modelMapper.map(dto, EnderecoEntity.class);
        EnderecoEntity savedEntity = repository.save(enderecoEntity);
        return modelMapper.map(savedEntity, EnderecoDTO.class);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        EnderecoEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), EnderecoDTO.class);
    }
}