package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    PizzaEntity findByNomePizza(String nome);

    PizzaEntity findByTamanho(Tamanho tamanho);

    PizzaEntity findByCategoria(Categoria categoria);
}
