package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

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