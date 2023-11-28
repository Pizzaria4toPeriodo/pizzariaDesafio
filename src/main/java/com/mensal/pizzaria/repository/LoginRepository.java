package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<FuncionarioEntity, Long> {


    public Optional<FuncionarioEntity> findByUsername(String login);

}
