package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.entity.EnderecoEntity;
import com.mensal.pizzaria.service.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Validated EnderecoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, EnderecoEntity.class)), EnderecoDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<EnderecoDTO>> getAll() {
        List<EnderecoDTO> list = new ArrayList<>();
        for (EnderecoEntity entity : service.getAll()) {
            EnderecoDTO map = modelMapper.map(entity, EnderecoDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), EnderecoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/rua/{rua}")
    public ResponseEntity<EnderecoDTO> getByRua(@PathVariable String rua) {
        return new ResponseEntity<>(modelMapper.map(service.getByRua(rua), EnderecoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/cliente/{nomeCliente}")
    public ResponseEntity<List<EnderecoDTO>> getEnderecosByNomeCliente(@PathVariable String nomeCliente) {
        List<EnderecoDTO> list = new ArrayList<>();

        for (EnderecoEntity entity : service.getEnderecosByNomeCliente(nomeCliente)) {
            EnderecoDTO dto = modelMapper.map(entity, EnderecoDTO.class);
            list.add(dto);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated EnderecoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, EnderecoEntity.class)), EnderecoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}