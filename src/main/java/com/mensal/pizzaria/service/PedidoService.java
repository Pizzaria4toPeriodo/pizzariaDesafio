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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public PedidoDTO getById(Long id) {
        Optional<PedidoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            PedidoEntity entity = optional.get();
            return modelMapper.map(entity, PedidoDTO.class);
        } else {
            throw new EntityNotFoundException("Pedido não encontrado com o ID: " + id);
        }
    }
    @Transactional
    public List<PedidoDTO> getAll() {
        List<PedidoDTO> list = new ArrayList<>();
        for (PedidoEntity entity : repository.findAll()) {
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public PedidoDTO create(PedidoDTO dto) {
        if (!dto.getProdutoList().isEmpty()) {
            dto.setTotal(calculoTotal(dto));
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        PedidoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), PedidoDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Double calculoTotal(PedidoDTO dto) {
        double total = 0.0;

        for (ProdutoDTO produto : dto.getProdutoList()) {
            total += produto.getPreco();
        }

        return total;
    }
}