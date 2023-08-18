package com.mensal.pizzaria.Service;

import com.mensal.pizzaria.DTO.EnderecoDTO;
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
    public void cadastrarEndereco(EnderecoDTO enderecoDto){
        Endereco endereco = new Endereco();
        endereco.getCliente();
        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());

        if(enderecoDto.getRua() == null || enderecoDto.getRua().isEmpty()) {
            throw new RuntimeException("Deve conter uma rua");
        }
        if ("".equals(enderecoDto.getNumero())){
            throw new RuntimeException("Deve conter um numero da rua");
        }

        enderecoRepository.save(endereco);
    }

    @Transactional
    public void atualizarEndereco(final Long id, EnderecoDTO enderecoDto){
        Endereco endereco = enderecoRepository.findById(id).orElse(null);

        if (endereco == null){
            throw new RuntimeException("Id do endereco nao existe!");
        }

        if(enderecoDto.getRua() == null || enderecoDto.getRua().isEmpty()) {
            throw new RuntimeException("Deve conter uma rua");
        }
        if ("".equals(enderecoDto.getNumero())){
            throw new RuntimeException("Deve conter um numero da rua");
        }

        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        enderecoRepository.save(endereco);
    }
}
