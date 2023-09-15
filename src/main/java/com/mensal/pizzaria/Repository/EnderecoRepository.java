package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    EnderecoEntity findByRua(String rua);
}
