package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.FuncionarioDto;
import com.mensal.pizzaria.Entity.Funcionario;
import com.mensal.pizzaria.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public void cadastraFuncionario(FuncionarioDto funcionarioDto){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setCargo(funcionarioDto.getCargo());
        funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void atualizaFuncionario(FuncionarioDto funcionarioDto){
        Funcionario funcionario;
        if (funcionarioDto.getId() != null) {
        funcionario = funcionarioRepository.findById(funcionarioDto.getId()).orElse(null);
        if (funcionario == null){
            throw new RuntimeException("Id do funcionario nao existe!");
        }
        else {
            funcionario = new Funcionario();
        }
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setCargo(funcionarioDto.getCargo());
        funcionarioRepository.save(funcionario);
        }
    }

}
