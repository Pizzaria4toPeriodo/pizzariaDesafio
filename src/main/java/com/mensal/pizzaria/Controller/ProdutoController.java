package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.DTO.ProdutoDTO;
import com.mensal.pizzaria.Entity.Produto;
import com.mensal.pizzaria.Repository.ProdutoRepository;
import com.mensal.pizzaria.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable("id") final long id) {
        try {
            final Produto produto = this.repository.findById(id).orElse(null);
            return ResponseEntity.ok(produto);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<String> buscarPorNome(@PathVariable("nome") final String nome) {
        try {
            Produto produto = repository.findByNome(nome);
            return ResponseEntity.ok(nome);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody final ProdutoDTO produtoDTO) {
        try {
            return ResponseEntity.ok(this.service.cadastrar(produtoDTO));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable("id") final Long id, @RequestBody final ProdutoDTO produtoDTO) {
        try {
            return ResponseEntity.ok(this.service.atualizar(id, produtoDTO));
        }
        catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") final Long id) {
        final Produto produtoBanco = this.repository.findById(id).orElse(null);
        try {
            this.repository.delete(produtoBanco);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
