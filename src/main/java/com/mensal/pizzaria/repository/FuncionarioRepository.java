package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    FuncionarioEntity findByNomeFuncionario(String nome);
}