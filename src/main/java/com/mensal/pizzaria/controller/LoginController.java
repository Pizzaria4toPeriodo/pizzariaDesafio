package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.login.LoginDTO;
import com.mensal.pizzaria.dto.login.UserDTO;
import com.mensal.pizzaria.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(service.login(loginDTO));
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("logout")
    public ResponseEntity<HttpStatus> logout() {
        SecurityContextHolder.clearContext();

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}