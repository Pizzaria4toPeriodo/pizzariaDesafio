package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.DTO.EnderecoDto;
import com.mensal.pizzaria.DTO.FuncionarioDto;
import com.mensal.pizzaria.Entity.Endereco;
import com.mensal.pizzaria.Entity.Funcionario;
import com.mensal.pizzaria.Repository.EnderecoRepository;
import com.mensal.pizzaria.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
