package com.mensal.pizzaria.Controller;


import com.mensal.pizzaria.Entity.Cliente;
import com.mensal.pizzaria.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Cliente cliente){
        try{
            clienteService.cadastrar(cliente);
            return ResponseEntity.ok("realizado com sucesso");
        }
        catch (RuntimeException e){
           return ResponseEntity.badRequest().body("error:" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") final Long id, @RequestBody final Cliente cliente) {
        try {
            this.clienteService.editar(cliente, id);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("error:" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") final Long id) {
        try {
            this.clienteService.excluir(id);
            return ResponseEntity.ok("Registro Excluido com sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("error:" + e.getMessage());
        }
    }
}