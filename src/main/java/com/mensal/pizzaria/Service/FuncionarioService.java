package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.FuncionarioDTO;
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
    public void cadastraFuncionario(FuncionarioDTO funcionarioDto){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setCargo(funcionarioDto.getCargo());

        if(funcionarioDto.getNome() == null || funcionarioDto.getNome().isEmpty()){
            throw new RuntimeException("deve conter um nome");
        }
        if (funcionarioDto.getNome().length() > 30){
            throw new RuntimeException("O nome de funcionario nao pode ter mais de 30 carateres");
        }
        if (funcionarioDto.getCargo() == null || funcionarioDto.getNome().isEmpty()){
            throw new RuntimeException("deve conter um cargo");
        }
        if (funcionarioDto.getCargo().length() > 30){
            throw new RuntimeException("O cargo de funcionario nao pode ter mais de 30 carateres");
        }

        funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void atualizaFuncionario(final Long id, FuncionarioDTO funcionarioDto){
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);

        if (funcionario == null){
            throw new RuntimeException("Id do funcionario nao existe!");
        }

        if(funcionarioDto.getNome() == null || funcionarioDto.getNome().isEmpty()){
            throw new RuntimeException("deve conter um nome");
        }
        if (funcionarioDto.getNome().length() > 30){
            throw new RuntimeException("O nome de funcionario nao pode ter mais de 30 carateres");
        }
        if (funcionarioDto.getCargo() == null || funcionarioDto.getNome().isEmpty()){
            throw new RuntimeException("deve conter um cargo");
        }
        if (funcionarioDto.getCargo().length() > 30){
            throw new RuntimeException("O cargo de funcionario nao pode ter mais de 30 carateres");
        }

        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setCargo(funcionarioDto.getCargo());
        funcionarioRepository.save(funcionario);
    }

}
