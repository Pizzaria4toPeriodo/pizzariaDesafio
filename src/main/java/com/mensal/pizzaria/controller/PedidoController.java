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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<PedidoDTO> create(@RequestBody @Validated PedidoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PedidoDTO>> getAll() {
        List<PedidoDTO> list = new ArrayList<>();
        for (PedidoEntity entity : service.getAll()) {
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), PedidoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/cliente/{nomeCliente}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByNomeCliente(@PathVariable String nomeCliente) {
        List<PedidoDTO> list = new ArrayList<>();

        for (PedidoEntity entity : service.getPedidosByNomeCliente(nomeCliente)) {
            PedidoDTO dto = modelMapper.map(entity, PedidoDTO.class);
            list.add(dto);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/funcionario/{nomeFuncionario}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByNomeFuncionario(@PathVariable String nomeFuncionario) {
        List<PedidoDTO> list = new ArrayList<>();

        for (PedidoEntity entity : service.getPedidosByNomeFuncionario(nomeFuncionario)) {
            PedidoDTO dto = modelMapper.map(entity, PedidoDTO.class);
            list.add(dto);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/delivery/{delivery}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByDelivery(@PathVariable boolean delivery) {
        List<PedidoDTO> list = new ArrayList<>();

        for (PedidoEntity entity : service.getPedidosByDelivery(delivery)) {
            PedidoDTO dto = modelMapper.map(entity, PedidoDTO.class);
            list.add(dto);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByData(@PathVariable("data") LocalDate data){
        List<PedidoDTO> list = new ArrayList<>();

        for (PedidoEntity entity : service.getPedidosByData(data)){
            PedidoDTO dto = modelMapper.map(entity, PedidoDTO.class);
            list.add(dto);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
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