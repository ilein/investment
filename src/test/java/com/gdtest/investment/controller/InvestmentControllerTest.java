package com.gdtest.investment.controller;

import com.gdtest.investment.InvestmentApplication;
import com.gdtest.investment.dao.BankDao;
import com.gdtest.investment.dao.ClientDao;
import com.gdtest.investment.dao.InvestmentDao;
import com.gdtest.investment.model.Bank;
import com.gdtest.investment.model.Client;
import com.gdtest.investment.model.Investment;
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

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InvestmentApplication.class)
@AutoConfigureMockMvc
@Transactional
class InvestmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InvestmentDao investmentDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private BankDao bankDao;

    @BeforeEach
    void setUp() {
        Investment investment = investmentDao.save(new Investment(
                clientDao.save(
                        new Client("Test Client Name",
                                "Test Client Short name",
                                "Test Client Address",
                                LegalFormEnum.OAO)),
                bankDao.save(
                        new Bank("Test Bank name", "999888777")),
                Date.valueOf("2020-09-25"),
                5,
                12
        ));
    }

    @Test
    void shouldReturnAll() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/investments/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[0].client.name", Matchers.is("Test Client Name")));
    }
}