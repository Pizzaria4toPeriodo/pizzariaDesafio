package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.service.FuncionarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<FuncionarioDTO> create(@RequestBody @Validated FuncionarioDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<FuncionarioDTO>> getAll() {
        List<FuncionarioDTO> list = new ArrayList<>();
        for (FuncionarioEntity entity : service.getAll()) {
            FuncionarioDTO map = modelMapper.map(entity, FuncionarioDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), FuncionarioDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<FuncionarioDTO> getByNomeFuncionario(@PathVariable String nome) {
        return new ResponseEntity<>(modelMapper.map(service.getByNomeFuncionario(nome), FuncionarioDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable("id") Long id, @RequestBody @Validated FuncionarioDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}