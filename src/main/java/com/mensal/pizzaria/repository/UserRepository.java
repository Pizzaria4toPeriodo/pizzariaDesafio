package com.mensal.pizzaria.repository;

import com.mensal.pizzaria.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   Optional< UserEntity> findByUsername(String username);
}
