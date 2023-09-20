package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.repository.PedidoRepository;
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
public class  PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<PedidoDTO> findAll() {
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
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id do pedido não deve ser inserido");
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        PedidoEntity existingEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));

        if (dto.getProdutoList().size() != existingEntity.getProdutoList().size()) {
            dto.setTotal(calculoTotal(dto));
        }

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), PedidoDTO.class);
    }

    public Double calculoTotal(PedidoDTO dto) {
        double total = 0.0;

        for (ProdutoDTO produto : dto.getProdutoList()) {
            total += produto.getPreco();
        }

        return total;
    }
}