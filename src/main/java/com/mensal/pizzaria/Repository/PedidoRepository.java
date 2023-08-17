package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
