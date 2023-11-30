package com.mensal.pizzaria.service;

import com.mensal.pizzaria.entity.UserEntity;
import com.mensal.pizzaria.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserEntity getId(long id) {
        Optional<UserEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public UserEntity create(UserEntity entity) {
        String ecodedPassword = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(ecodedPassword);

        return this.repository.save(entity);
    }
}