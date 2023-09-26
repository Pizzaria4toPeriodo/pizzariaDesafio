package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EnderecoDTO getById(Long id) {
        Optional<EnderecoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            EnderecoEntity entity = optional.get();
            return modelMapper.map(entity, EnderecoDTO.class);
        } else {
            throw new EntityNotFoundException("Endereço não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public EnderecoDTO getByRua(String rua) {
        return modelMapper.map(repository.findByRua(rua), EnderecoDTO.class);
    }

    @Transactional
    public List<EnderecoDTO> getAll() {
        List<EnderecoDTO> list = new ArrayList<>();
        for (EnderecoEntity entity : repository.findAll()) {
            EnderecoDTO map = modelMapper.map(entity, EnderecoDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public EnderecoDTO create(EnderecoDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, EnderecoEntity.class)), EnderecoDTO.class);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        EnderecoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), EnderecoDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}