package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.repository.ClienteRepository;
import com.mensal.pizzaria.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> getByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok().body(service.getByCpf(cpf));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteDTO> create(@RequestBody @Validated ClienteDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") Long id, @RequestBody @Validated ClienteDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}