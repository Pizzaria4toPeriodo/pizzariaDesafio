package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.entity.UserEntity;
import com.mensal.pizzaria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody @Validated UserEntity entity) {
        return new ResponseEntity<>(service.create(entity), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getId(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
    }
}