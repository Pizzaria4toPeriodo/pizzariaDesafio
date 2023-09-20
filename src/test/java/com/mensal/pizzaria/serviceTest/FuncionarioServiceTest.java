package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.dto.EnderecoDTO;
import com.mensal.pizzaria.dto.FuncionarioDTO;
import com.mensal.pizzaria.entity.FuncionarioEntity;
import com.mensal.pizzaria.repository.FuncionarioRepository;
import com.mensal.pizzaria.service.FuncionarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FuncionarioServiceTest {

    @MockBean
    private FuncionarioService funcionarioService;

    @MockBean
    private FuncionarioRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void injectData() {
        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "Marcelo", "caixa");

        List<FuncionarioDTO> funcionarioDTOList = new ArrayList<>();
        funcionarioDTOList.add(new FuncionarioDTO(1L, "Juan", "caixa"));

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(1L, "Marcelo", "caixa");

        Mockito.when(repository.save(Mockito.any(FuncionarioEntity.class))).thenReturn(new FuncionarioEntity());
        Mockito.when(repository.save(funcionario)).thenReturn(funcionario);
        Mockito.when(funcionarioService.create(funcionarioDTO)).thenReturn(funcionarioDTO);
        Mockito.when(funcionarioService.findByNomeFuncionario("Marcelo")).thenReturn(funcionarioDTO);
        Mockito.when(funcionarioService.findAll()).thenReturn(funcionarioDTOList);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testByNome() {

        FuncionarioDTO funcionario = funcionarioService.findByNomeFuncionario("Marcelo");
        List<FuncionarioDTO> funcionarios = new ArrayList<>();
        funcionarios.add(funcionario);

        Assertions.assertEquals(1, funcionarios.size());

        Assertions.assertEquals("Marcelo", funcionarios.get(0).getNomeFuncionario());
    }

    @Test
    void testLista() {

        List<FuncionarioDTO> funcionarioDTOLista = funcionarioService.findAll();

        Assertions.assertNotNull(funcionarioDTOLista);
        Assertions.assertEquals(1, funcionarioDTOLista.size());
    }
}
