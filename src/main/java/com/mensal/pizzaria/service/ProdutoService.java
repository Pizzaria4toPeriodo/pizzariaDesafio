package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> 89d15734e6678d16d002998f3903345b3cbf1544

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
<<<<<<< HEAD
        List<ProdutoDTO> list = new ArrayList<>();
        for (ProdutoEntity entity : repository.findAll()) {
            ProdutoDTO map = modelMapper.map(entity, ProdutoDTO.class);
            list.add(map);
        }
        return list;
=======
        return repository.findAll().stream().map(entity -> modelMapper.map(entity, ProdutoDTO.class)).collect(Collectors.toList());
>>>>>>> 89d15734e6678d16d002998f3903345b3cbf1544
    }

    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        ProdutoEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), ProdutoDTO.class);
    }
}