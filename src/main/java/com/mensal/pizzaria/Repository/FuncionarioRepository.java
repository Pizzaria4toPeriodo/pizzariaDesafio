package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    FuncionarioEntity findByNomeFuncionario(String nome);
}