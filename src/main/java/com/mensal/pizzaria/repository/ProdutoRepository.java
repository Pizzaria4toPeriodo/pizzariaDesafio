package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    ProdutoEntity findByNomeProduto(String nome);
}