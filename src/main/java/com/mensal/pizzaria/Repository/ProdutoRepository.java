package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    ProdutoEntity findByNomeProduto(String nome);
}
