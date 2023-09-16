package com.mensal.pizzaria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PizzariaApplicationTests {

	@Test
	void contextLoads() {
		int i = 1 + 2;
		Assertions.assertEquals(3, i);
	}

}
