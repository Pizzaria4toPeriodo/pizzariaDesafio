package com.mensal.pizzaria.Controller;


import com.mensal.pizzaria.DTO.ClienteDto;
import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    ClienteService clienteService;


    @GetMapping()
    public ResponseEntity<List<Cliente>> findAll() {

        try {
            return ResponseEntity.ok(clienteService.findAll());
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());


        }


    }
}