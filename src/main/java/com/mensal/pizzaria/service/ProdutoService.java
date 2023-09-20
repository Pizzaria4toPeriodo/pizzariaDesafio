package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ProdutoDTO findByNomeProduto(String nome) {
        return modelMapper.map(repository.findByNomeProduto(nome), ProdutoDTO.class);
    }

    @Transactional
    public List<ProdutoDTO> findAll() {
        List<ProdutoDTO> list = new ArrayList<>();
        for (ProdutoEntity entity : repository.findAll()) {
            ProdutoDTO map = modelMapper.map(entity, ProdutoDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        ProdutoEntity existingEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), ProdutoDTO.class);
    }
}