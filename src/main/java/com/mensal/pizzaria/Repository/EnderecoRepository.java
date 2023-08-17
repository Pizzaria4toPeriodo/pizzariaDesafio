package com.mensal.pizzaria.Repository;

import com.mensal.pizzaria.Entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Endereco findByRua (String rua);
}
