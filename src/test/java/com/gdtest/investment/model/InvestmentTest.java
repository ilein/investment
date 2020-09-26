package com.gdtest.investment.model;

import com.gdtest.investment.InvestmentApplication;
import com.gdtest.investment.dao.BankDao;
import com.gdtest.investment.dao.ClientDao;
import com.gdtest.investment.dao.InvestmentDao;
import com.gdtest.investment.model.enums.LegalFormEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InvestmentApplication.class)
@Transactional
class InvestmentTest {
    @Autowired
    private InvestmentDao investmentDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private BankDao bankDao;

    @Test
    public void whenSaveAndRetreiveInvestment_thenOK() {
        Investment investment = investmentDao.save(new Investment(
                clientDao.save(
                    new Client("Test name", "Test short name", "Test addr", LegalFormEnum.OAO)),
                bankDao.save(new Bank("Test bank name", "999888777")),
                Date.valueOf("2020-09-25"),
                5,
                12
        ));
        System.out.println(investment.toString());

        Investment investmentTst = investmentDao.getOne(investment.getId());
        assertNotNull(investmentTst);
        assertEquals(investment.toString(), investmentTst.toString());
    }
}