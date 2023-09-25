package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.repository.ProdutoRepository;
import com.mensal.pizzaria.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<ProdutoDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoDTO> getByNomeProduto(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(service.getByNomeProduto(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoDTO> create(@RequestBody @Validated ProdutoDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated ProdutoDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}