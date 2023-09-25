package com.mensal.pizzaria.service;

import com.mensal.pizzaria.dto.PedidoDTO;
import com.mensal.pizzaria.dto.ProdutoDTO;
import com.mensal.pizzaria.entity.PedidoEntity;
import com.mensal.pizzaria.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public PedidoDTO getById(Long id) {
        Optional<PedidoEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), PedidoDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encotrado");
        }
    }

    @Transactional
    public List<PedidoDTO> getAll() {
        List<PedidoDTO> list = new ArrayList<>();
        for (PedidoEntity entity : repository.findAll()) {
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public PedidoDTO create(PedidoDTO dto) {
        if (!dto.getProdutoList().isEmpty()) {
            dto.setTotal(calculoTotal(dto));
        }

        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        PedidoEntity existingEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));

        if (dto.getProdutoList().size() != existingEntity.getProdutoList().size()) {
            dto.setTotal(calculoTotal(dto));
        }

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), PedidoDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Double calculoTotal(PedidoDTO dto) {
        double total = 0.0;

        for (ProdutoDTO produto : dto.getProdutoList()) {
            total += produto.getPreco();
        }

        return total;
    }
}