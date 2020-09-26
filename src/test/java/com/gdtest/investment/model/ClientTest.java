package com.gdtest.investment.model;

import com.gdtest.investment.InvestmentApplication;
import com.gdtest.investment.dao.ClientDao;
import com.gdtest.investment.model.enums.LegalFormEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InvestmentApplication.class)
@Transactional
class ClientTest {
    @Autowired
    private ClientDao clientDao;

    @Test
    public void whenSaveAndRetreiveClient_thenOK() {
        Client client = clientDao.save(new Client("Test name", "Test short name", "Test addr", LegalFormEnum.OAO));
        Client clientTst = clientDao.getOne(client.getId());
        System.out.println(client.toString());
        assertNotNull(clientTst);
        assertEquals(client.toString(), clientTst.toString());
    }
}