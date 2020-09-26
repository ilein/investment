package com.gdtest.investment.controller;

import com.gdtest.investment.InvestmentApplication;
import com.gdtest.investment.dao.ClientDao;
import com.gdtest.investment.model.Client;
import com.gdtest.investment.model.enums.LegalFormEnum;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InvestmentApplication.class)
@AutoConfigureMockMvc
@Transactional
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientDao clientDao;

    @BeforeEach
    void setUp() {
        Client client = new Client("Test Client Name",
                "Test Client Short Name",
                "Test Client Address",
                LegalFormEnum.OAO);
        clientDao.save(client);
    }

    @Test
    void shouldReadAll() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[0].name", Matchers.is("Test Client Name")));
    }
}