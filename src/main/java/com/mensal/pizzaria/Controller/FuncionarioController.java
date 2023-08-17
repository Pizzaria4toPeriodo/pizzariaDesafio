package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.DTO.FuncionarioDto;
import com.mensal.pizzaria.Entity.Funcionario;
import com.mensal.pizzaria.Repository.FuncionarioRepository;
import com.mensal.pizzaria.Service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping()
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.funcionarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id") final long id
    ) {
        final Funcionario funcionario = this.funcionarioRepository.findById(id).orElse(null);
        return funcionario == null
                ? ResponseEntity.badRequest().body("Sem valor encontrado.")
                : ResponseEntity.ok(funcionario);
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> cadastraFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
        try {
            funcionarioService.cadastraFuncionario(funcionarioDto);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable("id") final Long id,
            @RequestBody final FuncionarioDto funcionarioDto
    ) {
        try {
            this.funcionarioService.atualizaFuncionario(id, funcionarioDto);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error:" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") final Long id){
        final Funcionario funcionario = this.funcionarioRepository.findById(id).orElse(null);
        try{
            this.funcionarioRepository.delete(funcionario);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("ID nao encontrado nao pode excluir");
        }
    }
}

