package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
