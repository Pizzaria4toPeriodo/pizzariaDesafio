package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;
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
            return modelMapper.map(optional.get(), EnderecoDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encotrado");
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
        EnderecoEntity existingEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), EnderecoDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}