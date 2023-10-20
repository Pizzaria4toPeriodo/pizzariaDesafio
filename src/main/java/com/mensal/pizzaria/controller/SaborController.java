package com.mensal.pizzaria.controller;

import com.mensal.pizzaria.dto.SaborDTO;
import com.mensal.pizzaria.entity.SaborEntity;
import com.mensal.pizzaria.entity.enums.Categoria;
import com.mensal.pizzaria.service.SaborService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sabores")
public class SaborController {
    @Autowired
    private SaborService service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<SaborDTO> create(@RequestBody @Validated SaborDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, SaborEntity.class)), SaborDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<SaborDTO>> getAll() {
        List<SaborDTO> list = new ArrayList<>();
        for (SaborEntity entity : service.getAll()) {
            SaborDTO map = modelMapper.map(entity, SaborDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaborDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), SaborDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<SaborDTO> getByNomeSabor(@PathVariable String nome) {
        return new ResponseEntity<>(modelMapper.map(service.getByNomeSabor(nome), SaborDTO.class), HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<SaborDTO> getByCategoria(@PathVariable Categoria categoria) {
        return new ResponseEntity<>(modelMapper.map(service.getByCategoria(categoria), SaborDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaborDTO> update(@PathVariable("id") Long id, @RequestBody @Validated SaborDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, SaborEntity.class)), SaborDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
