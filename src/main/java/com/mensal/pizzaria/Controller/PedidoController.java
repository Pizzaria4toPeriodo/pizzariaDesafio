package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.DTO.PedidoDTO;
import com.mensal.pizzaria.Entity.Pedido;
import com.mensal.pizzaria.Repository.PedidoRepository;
import com.mensal.pizzaria.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final long id) {
        final Pedido pedido = this.repository.findById(id).orElse(null);
        return pedido == null ? ResponseEntity.badRequest().body("Nenhum registro encontrado") : ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody PedidoDTO pedidoDTO) {
        try {
            this.service.cadastrar(pedidoDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Cadastro realizado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedidoDTO) {
        try {
            this.service.atualizar(id, pedidoDTO);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error:" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") final Long id){
        final Pedido pedido = this.repository.findById(id).orElse(null);
        try{
            this.repository.delete(pedido);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("ID inválido ou não existe");
        }
    }
}
