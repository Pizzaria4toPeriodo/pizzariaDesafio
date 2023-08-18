package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.ProdutoDTO;
import com.mensal.pizzaria.Entity.Produto;
import com.mensal.pizzaria.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional
    public Produto cadastrar(@RequestBody final ProdutoDTO produtoDTO) {
        if (produtoDTO.getId() != null) {
            throw new RuntimeException("o campo ID não deve ser inserido");
        }
        Produto produto = mapper.map(produtoDTO, Produto.class);
        //Produto produto = toProdutoDTO(produtoDTO);
        return this.repository.save(produto);
    }

    @Transactional
    public void atualizar(final Long id, ProdutoDTO produtoDTO) {
        final Produto produtoBanco = this.repository.findById(id).orElse(null);
        if (produtoBanco != null || !produtoBanco.getId().equals(produtoDTO.getId())) {
            throw new RuntimeException("não foi possível encontrar o registro informado");
        }
        Produto produto = mapper.map(produtoDTO, Produto.class);
        //Produto produto = toProdutoDTO(produtoDTO);
        this.repository.save(produto);
    }

    /*public Produto toProdutoDTO(ProdutoDTO produtoDTO){
        Produto produtoTemp = new Produto();
        produtoTemp.setNome(produtoDTO.getNome());
        produtoTemp.setPreco(produtoDTO.getPreco());
        return produtoTemp;
    }*/
}
