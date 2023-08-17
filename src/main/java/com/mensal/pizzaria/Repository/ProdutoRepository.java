package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByNome(String nome);
}
