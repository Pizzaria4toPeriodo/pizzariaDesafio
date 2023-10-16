package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.service.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@RequestBody @Validated PedidoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PedidoDTO>> getAll() {
        List<PedidoDTO> list = new ArrayList<>();
        for (PedidoEntity entity : service.getAll()) {
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PedidoDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), PedidoDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated PedidoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}