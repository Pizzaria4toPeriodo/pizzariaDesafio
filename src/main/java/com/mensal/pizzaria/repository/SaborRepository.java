package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.SaborEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaborRepository extends JpaRepository<SaborEntity, Long> {
    SaborEntity findByNomeSabor(String nome);
}
