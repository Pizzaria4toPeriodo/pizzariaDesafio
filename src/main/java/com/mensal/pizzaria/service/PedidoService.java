package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.ProdutoEntity;
import com.mensal.pizzaria.entity.enums.Forma_Pagamento;
import com.mensal.pizzaria.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public Double calculoProdutos(PedidoEntity entity) {
        double total = 0.0;

        for (ProdutoEntity produto : entity.getProdutoList()) {
            if (produto.getPreco() != null) {
                total += produto.getPreco();
            }
        }

        return total;
    }

    public Double calculoPizzas(PedidoEntity entity) {
        double total = 0.0;

        for (PizzaEntity pizza : entity.getPizzaList()) {
            if (pizza.getPreco() != null) {
                total += pizza.getPreco();
            }
        }

        return total;
    }

    public void calculoTotal(PedidoEntity entity) {
        double precoProdutos = 0;
        double precoPizzas = 0;

        if (!entity.getProdutoList().isEmpty()) {
            precoProdutos = calculoProdutos(entity);
        }

        if (!entity.getPizzaList().isEmpty()) {
            precoPizzas = calculoPizzas(entity);
        }

        if (entity.getFormaPagamento() == Forma_Pagamento.PIX || entity.getFormaPagamento() == Forma_Pagamento.DINHEIRO) {
            double desconto = (precoProdutos + precoPizzas) * 0.1;
            entity.setTotal((precoProdutos + precoPizzas) - desconto); // Aplica o desconto ao total
        } else {
            entity.setTotal(precoProdutos + precoPizzas); // Sem desconto
        }
    }

    @Transactional
    public PedidoEntity create(PedidoEntity entity) {
        calculoTotal(entity);

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
    public List<PedidoEntity> getPedidosByDelivery(boolean delivery) {
        return repository.findByDelivery(delivery);
    }

    @Transactional
    public List<PedidoEntity> getPedidosByData(LocalDate data) {
        return this.repository.findByData(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
    }

    @Transactional
    public PedidoEntity update(Long id, PedidoEntity entity) {
        PedidoEntity existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));

        calculoTotal(entity);

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}