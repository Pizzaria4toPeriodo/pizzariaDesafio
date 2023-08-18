package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.DTO.EnderecoDto;
import com.mensal.pizzaria.DTO.FuncionarioDto;
import com.mensal.pizzaria.Entity.Endereco;
import com.mensal.pizzaria.Entity.Funcionario;
import com.mensal.pizzaria.Repository.EnderecoRepository;
import com.mensal.pizzaria.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.enderecoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id") final long id
    ) {
        final Endereco endereco = this.enderecoRepository.findById(id).orElse(null);
        return endereco == null
                ? ResponseEntity.badRequest().body("Sem valor encontrado.")
                : ResponseEntity.ok(endereco);
    }


    @GetMapping("/buscar/{rua}")
    public ResponseEntity<?> buscarPorRua(@PathVariable("rua") String rua) {
        Endereco endereco = enderecoRepository.findByRua(rua);

        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("endereco com rua '" + rua + "' não encontrado.");
        }
        return ResponseEntity.ok(endereco);
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> cadastraFuncionario(@RequestBody EnderecoDto enderecoDto) {
        try {
            enderecoService.cadastrarEndereco(enderecoDto);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable("id") final Long id,
            @RequestBody final EnderecoDto enderecoDto
    ) {
        try {
            this.enderecoService.atualizarEndereco(id, enderecoDto);
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
        final Endereco endereco = this.enderecoRepository.findById(id).orElse(null);
        try{
            this.enderecoRepository.delete(endereco);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("ID nao encontrado nao pode excluir");
        }
    }
}
