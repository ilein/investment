package com.gdtest.investment.service;

import com.gdtest.investment.dao.BankDao;
import com.gdtest.investment.model.Bank;
import com.gdtest.investment.specification.BankSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankDao bankDao;

    @Override
    public Bank create(Bank bank) {
        return this.bankDao.save(bank);
    }

    @Override
    public List<Bank> readAll() {
        return this.bankDao.findAll();
    }

    @Override
    public List<Bank> readAllFilter(Integer pageNo, Integer pageSize, String sortBy, String direction, String search) {
        Pageable paging =Utils.getPaging(pageSize, pageNo, sortBy, direction);

        Page<Bank> pagedResult;
        if (!"".equals(search)) {
            Specification<Bank> spec = Specification.where(
                    BankSpecification.nameContain(search))
                    .or(BankSpecification.bicContain(search));

            pagedResult = bankDao.findAll(spec, paging);
        }
        else {
            pagedResult = bankDao.findAll(paging);
        }
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Bank> read(int id) {
        return this.bankDao.findById(id);
    }

    @Override
    public boolean update(Bank bank, int id) {
        if (this.bankDao.existsById(id)) {
            bank.setId(id);
            bankDao.save(bank);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (this.bankDao.existsById(id)) {
            this.bankDao.deleteById(id);
            return true;
        }
        return false;
    }
}
