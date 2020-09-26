package com.gdtest.investment.model;

import com.gdtest.investment.InvestmentApplication;
import com.gdtest.investment.dao.BankDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InvestmentApplication.class)
@Transactional
class BankTest {
    @Autowired
    private BankDao bankDao;

    @Test
    public void whenSaveAndRetreiveBank_thenOK() {
        Bank bank = bankDao
                .save(new Bank("Test bank name", "099989"));
        Bank bankTst = bankDao.getOne(bank.getId());
        System.out.println(bank.toString());
        assertNotNull(bankTst);
        assertEquals(bank.toString(), bankTst.toString());
    }
}