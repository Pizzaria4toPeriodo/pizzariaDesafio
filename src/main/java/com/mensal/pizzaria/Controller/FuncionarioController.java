package com.mensal.pizzaria.Controller;

import com.mensal.pizzaria.Entity.Funcionario;
import com.mensal.pizzaria.Repository.FuncionarioRepository;
import com.mensal.pizzaria.Service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
