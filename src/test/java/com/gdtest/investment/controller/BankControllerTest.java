package com.gdtest.investment.controller;

import com.gdtest.investment.InvestmentApplication;
import com.gdtest.investment.dao.BankDao;
import com.gdtest.investment.model.Bank;
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
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BankDao bankDao;

    @BeforeEach
    void setUp() {
        Bank bank = new Bank("Name to Test", "099989586");
        bankDao.save(bank);
    }

    @Test
    void shouldReadAll() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/banks/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[0].name", Matchers.is("Name to Test")));
    }
}