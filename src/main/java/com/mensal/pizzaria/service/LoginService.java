package com.mensal.pizzaria.service;

import com.mensal.pizzaria.config.jwt.JwtServiceGenerator;
import com.mensal.pizzaria.entity.UserEntity;
import com.mensal.pizzaria.dto.login.LoginDTO;
import com.mensal.pizzaria.dto.login.UserDTO;
import com.mensal.pizzaria.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository repository;
    @Autowired
    private JwtServiceGenerator jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        UserEntity user = repository.findByUsername(loginDTO.username()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return toUserDTO(user, jwtToken);
    }

    private UserDTO toUserDTO(UserEntity user, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setToken(token);
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}