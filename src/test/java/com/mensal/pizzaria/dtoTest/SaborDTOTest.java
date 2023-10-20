package com.mensal.pizzaria.dtoTest;

import com.mensal.pizzaria.dto.SaborDTO;
import com.mensal.pizzaria.entity.enums.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SaborDTOTest {
    @Test
    void testDTO() {
        SaborDTO sabor = new SaborDTO(1L, "Calabresa", Categoria.TRADICIONAL);

        Assertions.assertEquals(1L, sabor.getId());
        Assertions.assertEquals("Calabresa", sabor.getNomeSabor());
        Assertions.assertEquals(Categoria.TRADICIONAL, sabor.getCategoria());
    }

    @Test
    void testDTOSetter() {
        SaborDTO sabor = new SaborDTO();

        sabor.setId(1L);
        sabor.setNomeSabor("Calabresa");
        sabor.setCategoria(Categoria.TRADICIONAL);

        Assertions.assertEquals(1L, sabor.getId());
        Assertions.assertEquals("Calabresa", sabor.getNomeSabor());
        Assertions.assertEquals(Categoria.TRADICIONAL, sabor.getCategoria());
    }
}
