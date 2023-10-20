package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.FuncionarioDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FuncionarioDTOTest {
    @Test
    void testDTO() {
        FuncionarioDTO funcionario = new FuncionarioDTO(1L, "Gustavo", "Entregador", null);

        Assertions.assertEquals(1L, funcionario.getId());
        Assertions.assertEquals("Gustavo", funcionario.getNomeFuncionario());
        Assertions.assertEquals("Entregador", funcionario.getCargo());
        Assertions.assertNull(funcionario.getPedidoList());
    }

    @Test
    void testDTOSetter() {
        FuncionarioDTO funcionario = new FuncionarioDTO();

        funcionario.setId(1L);
        funcionario.setNomeFuncionario("Gustavo");
        funcionario.setCargo("Entregador");
        funcionario.setPedidoList(null);

        Assertions.assertEquals(1L, funcionario.getId());
        Assertions.assertEquals("Gustavo", funcionario.getNomeFuncionario());
        Assertions.assertEquals("Entregador", funcionario.getCargo());
        Assertions.assertNull(funcionario.getPedidoList());

    }
}
