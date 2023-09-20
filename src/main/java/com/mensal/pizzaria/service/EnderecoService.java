package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EnderecoDTO findByRua(String rua) {
        return modelMapper.map(repository.findByRua(rua), EnderecoDTO.class);
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

        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id do endereço não deve ser inserido");
        }

        return modelMapper.map(savedEntity, EnderecoDTO.class);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        EnderecoEntity existingEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), EnderecoDTO.class);
    }
}