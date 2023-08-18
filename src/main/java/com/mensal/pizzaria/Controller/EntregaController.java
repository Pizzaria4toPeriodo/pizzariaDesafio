package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.Entity.Entrega;
import com.mensal.pizzaria.Service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    EntregaService entregaService;

    @GetMapping()
    public ResponseEntity<List<Entrega>> findAll() {
        try {
            return ResponseEntity.ok(entregaService.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Entrega entrega){
        try{
            entregaService.cadastrar(entrega);
            return ResponseEntity.ok("realizado com sucesso");
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") final Long id, @RequestBody final Entrega entrega) {
        try {
            this.entregaService.editar(entrega, id);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("error:" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") final Long id) {
        try {
            this.entregaService.excluir(id);
            return ResponseEntity.ok("Registro Excluido com sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("error:" + e.getMessage());
        }
    }
}