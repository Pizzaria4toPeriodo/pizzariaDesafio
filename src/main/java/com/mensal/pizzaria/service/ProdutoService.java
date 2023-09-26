package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ProdutoDTO getById(Long id) {
        Optional<ProdutoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            ProdutoEntity entity = optional.get();
            return modelMapper.map(entity, ProdutoDTO.class);
        } else {
            throw new EntityNotFoundException("Produto não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public ProdutoDTO getByNomeProduto(String nome) {
        return modelMapper.map(repository.findByNomeProduto(nome), ProdutoDTO.class);
    }

    @Transactional
    public List<ProdutoDTO> getAll() {
        List<ProdutoDTO> list = new ArrayList<>();
        for (ProdutoEntity entity : repository.findAll()) {
            ProdutoDTO map = modelMapper.map(entity, ProdutoDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        ProdutoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), ProdutoDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}