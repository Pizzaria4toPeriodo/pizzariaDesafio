package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import com.mensal.pizzaria.service.FuncionarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<FuncionarioDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FuncionarioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<FuncionarioDTO> getByNomeFuncionario(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(service.getByNomeFuncionario(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FuncionarioDTO> create(@RequestBody @Validated FuncionarioDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable("id") Long id, @RequestBody @Validated FuncionarioDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}