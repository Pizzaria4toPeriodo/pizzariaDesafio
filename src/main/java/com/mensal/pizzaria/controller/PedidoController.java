package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.repository.PedidoRepository;
import com.mensal.pizzaria.service.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<PedidoDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PedidoDTO> create(@RequestBody @Validated PedidoDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable("id") Long id, @RequestBody PedidoDTO dto) {
        try {
            return ResponseEntity.ok().body(service.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}