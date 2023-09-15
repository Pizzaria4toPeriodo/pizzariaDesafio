package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    EnderecoEntity findByRua(String rua);
}
