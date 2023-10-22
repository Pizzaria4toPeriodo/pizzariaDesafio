package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.PizzaDTO;
import com.mensal.pizzaria.entity.PizzaEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.entity.enums.Tamanho;
import com.mensal.pizzaria.service.PizzaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<PizzaDTO> create(@RequestBody @Validated PizzaDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, PizzaEntity.class)), PizzaDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PizzaDTO>> getAll() {
        List<PizzaDTO> list = new ArrayList<>();
        for (PizzaEntity entity : service.getAll()) {
            PizzaDTO map = modelMapper.map(entity, PizzaDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), PizzaDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<PizzaDTO> getByNomePizza(@PathVariable String nome) {
        return new ResponseEntity<>(modelMapper.map(service.getByNomePizza(nome), PizzaDTO.class), HttpStatus.OK);
    }

    @GetMapping("/tamanho/{tamanho}")
    public ResponseEntity<PizzaDTO> getByTamanho(@PathVariable Tamanho tamanho) {
        return new ResponseEntity<>(modelMapper.map(service.getByTamanho(tamanho), PizzaDTO.class), HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<PizzaDTO> getByCategoria(@PathVariable Categoria categoria) {
        return new ResponseEntity<>(modelMapper.map(service.getByCategoria(categoria), PizzaDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> update(@PathVariable("id") Long id, @RequestBody @Validated PizzaDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, PizzaEntity.class)), PizzaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
