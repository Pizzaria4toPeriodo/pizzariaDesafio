package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaborRepository extends JpaRepository<SaborEntity, Long> {
    SaborEntity findByNomeSabor(String nome);

    SaborEntity findByCategoria(Categoria categoria);
}
