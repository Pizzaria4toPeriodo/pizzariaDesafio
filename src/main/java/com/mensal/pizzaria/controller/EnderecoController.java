package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.repository.EnderecoRepository;
import com.mensal.pizzaria.service.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<EnderecoDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping("/rua/{rua}")
    public ResponseEntity<EnderecoDTO> getByRua(@PathVariable("rua") String rua) {
        return ResponseEntity.ok().body(service.getByRua(rua));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Validated EnderecoDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated EnderecoDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}