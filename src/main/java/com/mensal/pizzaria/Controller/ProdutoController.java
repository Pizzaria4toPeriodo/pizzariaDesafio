package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.DTO.ProdutoDTO;
import com.mensal.pizzaria.Entity.Produto;
import com.mensal.pizzaria.Repository.ProdutoRepository;
import com.mensal.pizzaria.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private EnderecoService service;
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final long id) {
        final Produto produto = this.repository.findById(id).orElse(null);
        return produto == null ? ResponseEntity.badRequest().body("Nenhum registro encontrado") : ResponseEntity.ok(produto);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable("nome") String nome) {
        Produto produto = repository.findByNome(nome);
        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar um produto com o nome: " + nome);
        }
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoDTO produtoDTO) {
        try {
            this.service.cadastrar(produtoDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Cadastro realizado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") final Long id, @RequestBody final ProdutoDTO produtoDTO) {
        try {
            this.service.atualizar(id, produtoDTO);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error:" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") final Long id){
        final Produto produto = this.repository.findById(id).orElse(null);
        try{
            this.repository.delete(produto);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("ID inválido ou não existe");
        }
    }
}
