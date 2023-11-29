package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
