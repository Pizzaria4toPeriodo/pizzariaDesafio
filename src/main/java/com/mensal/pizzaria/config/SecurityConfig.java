package com.mensal.pizzaria.config;


import com.mensal.pizzaria.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private FuncionarioService funcionarioService;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity

                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/funcionarios/teste").hasAuthority("ADMIN")
                        .requestMatchers("/funcionarios/list").hasAuthority("ADMIN")
                        .requestMatchers("/livre").permitAll()
                        .anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());


        return httpSecurity.build();

    }


    @Bean
    static PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    protected void authManager(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(funcionarioService).passwordEncoder(passwordEncoder());
    }






}
