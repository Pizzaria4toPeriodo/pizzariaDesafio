package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
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
        if (!dto.getProdutoList().isEmpty()) {
            dto.setTotal(calculoTotal(dto));
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        PedidoEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o registro informado"));

        if (dto.getProdutoList().size() != existingEntity.getProdutoList().size()) {
            dto.setTotal(calculoTotal(dto));
        }

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), PedidoDTO.class);
    }

    private Double calculoTotal(PedidoDTO dto) {
        double total = 0.0;

        for (ProdutoDTO produto : dto.getProdutoList()) {
            total += produto.getPreco();
        }

        return total;
    }
}