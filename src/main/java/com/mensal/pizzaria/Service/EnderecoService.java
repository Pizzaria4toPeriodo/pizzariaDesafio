package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.EnderecoDto;
import com.mensal.pizzaria.Entity.Endereco;
import com.mensal.pizzaria.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public void cadastrarEndereco(EnderecoDto enderecoDto){
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        enderecoRepository.save(endereco);
    }

    @Transactional
    public void atualizarEndereco(final Long id, EnderecoDto enderecoDto){
        Endereco endereco = enderecoRepository.findById(id).orElse(null);

        if (endereco == null){
            throw new RuntimeException("Id do endereco nao existe!");
        }

        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        enderecoRepository.save(endereco);
    }
}
