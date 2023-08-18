package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.PedidoDTO;
import com.mensal.pizzaria.Entity.Pedido;
import com.mensal.pizzaria.Entity.Produto;
import com.mensal.pizzaria.Repository.PedidoRepository;
import com.mensal.pizzaria.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Transactional
    public Pedido cadastrar(@RequestBody final PedidoDTO pedidoDTO) {
        if (pedidoDTO.getId() != null) {
            throw new RuntimeException("o campo ID não deve ser inserido");
        }
        Pedido pedido = toPedidoDTO(pedidoDTO);
        return this.repository.save(pedido);
    }

    @Transactional
    public void atualizar(final Long id, PedidoDTO pedidoDTO) {
        final Pedido pedidoBanco = this.repository.findById(id).orElse(null);
        if (pedidoBanco != null || !pedidoBanco.getId().equals(pedidoDTO.getId())) {
            throw new RuntimeException("não foi possível encontrar o registro informado");
        }
        Pedido pedido = toPedidoDTO(pedidoDTO);
        this.repository.save(pedido);
    }

    public Pedido toPedidoDTO(PedidoDTO pedidoDTO){
        Pedido pedidoTemp = new Pedido();
        return pedidoTemp;
    }
}
