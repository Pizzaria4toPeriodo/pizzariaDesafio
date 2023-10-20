package com.mensal.pizzaria.entityTest;

import com.mensal.pizzaria.entity.FuncionarioEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FuncionarioTest {
    @Test
    void entitySetterTest() {
        FuncionarioEntity funcionario = new FuncionarioEntity();

        funcionario.setId(1L);
        funcionario.setNomeFuncionario("Gustavo");
        funcionario.setPedidoList(null);

        Assertions.assertEquals(1L, funcionario.getId());
        Assertions.assertEquals("Gustavo", funcionario.getNomeFuncionario());
        Assertions.assertNull(funcionario.getPedidoList());
    }
}