package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    FuncionarioEntity findByNomeFuncionario(String nome);
    UserDetails findByUsername(String username);
}