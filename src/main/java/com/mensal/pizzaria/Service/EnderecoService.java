package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.EnderecoDTO;
import com.mensal.pizzaria.Entity.EnderecoEntity;
import com.mensal.pizzaria.Repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findAll().stream().map(entity -> modelMapper.map(entity, EnderecoDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public EnderecoDTO create(EnderecoDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, EnderecoEntity.class)), EnderecoDTO.class);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, EnderecoEntity.class)), EnderecoDTO.class);
    }
}