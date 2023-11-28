package com.mensal.pizzaria.service;


import com.mensal.pizzaria.config.JwtServiceGenerator;
import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.dto.LoginDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
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

    private AuthenticationManager authenticationManager;


    public FuncionarioDTO logar(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        FuncionarioEntity funcionario = repository.findByUsername(loginDTO.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(funcionario);

        return toUserDTO(funcionario, jwtToken);
    }


    private FuncionarioDTO toUserDTO(FuncionarioEntity funcionario, String token) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setRole(funcionario.getRole());
        funcionarioDTO.setToken(token);
        funcionarioDTO.setUsername(funcionario.getUsername());
        return funcionarioDTO;
    }



}
