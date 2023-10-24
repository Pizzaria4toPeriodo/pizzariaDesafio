package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    @Query("SELECT e FROM PedidoEntity e WHERE e.cliente.nomeCliente = :nomeCliente")
    List<PedidoEntity> findPedidosByCliente(String nomeCliente);
    @Query("SELECT e FROM PedidoEntity e WHERE e.funcionario.nomeFuncionario = :nomeFuncionario")
    List<PedidoEntity> findPedidosByFuncionario(String nomeFuncionario);

    List<PedidoEntity> findByDelivery(boolean delivery);

    @Query("SELECT p FROM PedidoEntity p WHERE " +
            "EXTRACT(YEAR FROM p.criadoEm) = :ano AND " +
            "EXTRACT(MONTH FROM p.criadoEm) = :mes AND " +
            "EXTRACT(DAY FROM p.criadoEm) = :dia")
    List<PedidoEntity> findByData(@Param("ano") int ano, @Param("mes") int mes, @Param("dia") int dia);
}
