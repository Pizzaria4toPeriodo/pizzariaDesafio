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

    public Double calculoTotal(PedidoEntity entity) {
        double total = 0.0;

        for (ProdutoEntity produto : entity.getProdutoList()) {
            if (produto.getPreco() != null) {
                total += produto.getPreco();
            }
        }

        return total;
    }

    @Transactional
    public PedidoEntity create(PedidoEntity entity) {
        if (!entity.getProdutoList().isEmpty()) {
            double total = calculoTotal(entity);
            entity.setTotal(total);
        }

        return repository.save(entity);
    }

    @Transactional
    public List<PedidoEntity> getAll() {
        return repository.findAll();
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
    public List<PedidoEntity> getPedidosByNomeCliente(String nome) {
        return repository.findPedidosByCliente(nome);
    }

    @Transactional
    public List<PedidoEntity> getPedidosByNomeFuncionario(String nome) {
        return repository.findPedidosByFuncionario(nome);
    }

    @Transactional
    public List<PedidoEntity> getByDelivery(boolean delivery) {
        return repository.findByDelivery(delivery);
    }

    @Transactional
    public PedidoEntity update(Long id, PedidoEntity entity) {
        PedidoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));

        if (!entity.getProdutoList().isEmpty()) {
            double total = calculoTotal(entity);
            entity.setTotal(total);
        }

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}