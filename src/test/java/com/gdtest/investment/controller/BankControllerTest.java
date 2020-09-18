package com.gdtest.investment.controller;

import com.gdtest.investment.model.Bank;
import com.gdtest.investment.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = BankController.class)
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    private List<Bank> bankList;

    @BeforeEach
    void setUp() {
        this.bankList =
                new ArrayList<>();
        this.bankList.add(new Bank(1000, "name1", "099989"));
        this.bankList.add(new Bank(1001, "name2", "199989"));
        this.bankList.add(new Bank(1002, "name3", "299989"));
    }

    @Test
    void shouldReadAll() throws Exception {
        BDDMockito.given(bankService.readAll()).willReturn(bankList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/banks/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}