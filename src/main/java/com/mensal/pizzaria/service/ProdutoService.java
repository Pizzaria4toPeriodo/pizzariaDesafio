package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class);
    }
}