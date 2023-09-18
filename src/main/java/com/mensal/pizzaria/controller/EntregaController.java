package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.ClienteDTO;
import com.mensal.pizzaria.dto.EntregaDTO;
import com.mensal.pizzaria.entity.EntregaEntity;
import com.mensal.pizzaria.repository.EntregaRepository;
import com.mensal.pizzaria.service.EntregaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private EntregaService service;
    @Autowired
    private EntregaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<EntregaDTO>> findAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(entity -> modelMapper.map(entity, EntregaDTO.class)).toList(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> findById(@PathVariable("id") Long id){

        try {
            EntregaDTO entregaDTO = this.service.findById(id);
            return ResponseEntity.ok(entregaDTO);
        }
        catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<EntregaDTO> create(@RequestBody @Validated EntregaDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<EntregaDTO> update(@PathVariable("id") Long id, @RequestBody @Validated EntregaDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            EntregaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
            repository.delete(entity);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}