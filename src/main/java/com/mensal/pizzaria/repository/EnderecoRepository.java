package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    EnderecoEntity findByRua(String rua);

    @Query("SELECT e FROM EnderecoEntity e WHERE e.cliente.nomeCliente = :nomeCliente")
    List<EnderecoEntity> findEnderecosByNomeCliente(String nomeCliente);
}
