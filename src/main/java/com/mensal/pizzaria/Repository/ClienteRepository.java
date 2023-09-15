package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByNomeCliente(String nomeCliente);

    ClienteEntity findByCpf(String cpf);
}