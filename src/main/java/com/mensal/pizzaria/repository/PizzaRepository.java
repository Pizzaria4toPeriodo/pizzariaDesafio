package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    PizzaEntity findByNomePizza(String nome);
}
