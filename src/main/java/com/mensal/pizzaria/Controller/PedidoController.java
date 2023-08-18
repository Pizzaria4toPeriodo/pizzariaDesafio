package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.DTO.PedidoDTO;
import com.mensal.pizzaria.DTO.ProdutoDTO;
import com.mensal.pizzaria.Entity.Pedido;
import com.mensal.pizzaria.Entity.Produto;
import com.mensal.pizzaria.Repository.PedidoRepository;
import com.mensal.pizzaria.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable("id") final long id) {
        try {
            final Pedido pedido = this.repository.findById(id).orElse(null);
            return ResponseEntity.ok(pedido);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody final PedidoDTO pedidoDTO) {
        try {
            return ResponseEntity.ok(this.service.cadastrar(pedidoDTO));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedidoDTO) {
        try {
            return ResponseEntity.ok(this.service.atualizar(id, pedidoDTO));
        }
        catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") final Long id) {
        final Pedido pedidoBanco = this.repository.findById(id).orElse(null);
        try {
            this.repository.delete(pedidoBanco);
            return ResponseEntity.ok("Pedido deletado com sucesso");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
