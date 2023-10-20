package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByNomeCliente(String nomeCliente);

    ClienteEntity findByCpf(String cpf);
}