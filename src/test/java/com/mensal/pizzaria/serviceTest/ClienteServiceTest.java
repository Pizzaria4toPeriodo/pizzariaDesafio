package com.mensal.pizzaria.serviceTest;

import com.mensal.pizzaria.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ClienteServiceTest {
    @MockBean
    private ClienteRepository repository;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testCreate() {

    }
}
