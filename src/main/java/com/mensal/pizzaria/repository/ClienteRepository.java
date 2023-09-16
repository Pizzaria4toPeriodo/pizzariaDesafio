package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByNomeCliente(String nomeCliente);

    ClienteEntity findByCpf(String cpf);
}