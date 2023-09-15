package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.PedidoDTO;
import com.mensal.pizzaria.Entity.PedidoEntity;
import com.mensal.pizzaria.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<PedidoDTO> findAll() {
        return repository.findAll().stream().map(entity -> modelMapper.map(entity, PedidoDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public PedidoDTO create(PedidoDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }
}