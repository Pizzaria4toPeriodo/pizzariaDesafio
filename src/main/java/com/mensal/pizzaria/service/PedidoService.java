package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public PedidoEntity create(PedidoEntity entity) {
        if (!entity.getProdutoList().isEmpty()) {
            entity.setTotal(calculoTotal(entity));
        }

        return repository.save(entity);
    }

    @Transactional
    public PedidoEntity getById(Long id) {
        Optional<PedidoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Pedido não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public List<PedidoEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public PedidoEntity update(Long id, PedidoEntity entity) {
        PedidoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Double calculoTotal(PedidoEntity entity) {
        double total = 0.0;

        for (ProdutoEntity produto : entity.getProdutoList()) {
            total += produto.getPreco();
        }

        return total;
    }
}