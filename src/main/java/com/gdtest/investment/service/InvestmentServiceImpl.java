package com.gdtest.investment.service;

import com.gdtest.investment.dao.InvestmentDao;
import com.gdtest.investment.model.Investment;
import com.gdtest.investment.specification.InvestmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {
    @Autowired
    InvestmentDao investmentDao;


    @Override
    public Investment create(Investment investment) {
        return this.investmentDao.save(investment);
    }

    @Override
    public List<Investment> readAll() {
        return this.investmentDao.findAll();
    }

    @Override
    public List<Investment> readAllFilter(Integer pageNo, Integer pageSize, String sortBy, String direction, String search) {
        Pageable paging =Utils.getPaging(pageSize, pageNo, sortBy, direction);

        Page<Investment> pagedResult;
        if (!"".equals(search)) {
            Specification<Investment> spec = Specification.where(
                    InvestmentSpecification.clientNameContain(search))
                    .or(InvestmentSpecification.bankNameContain(search));

            pagedResult = investmentDao.findAll(spec, paging);
        }
        else {
            pagedResult = investmentDao.findAll(paging);
        }
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Investment> read(int id) {
        return this.investmentDao.findById(id);
    }

    @Override
    public boolean update(Investment investment, int id) {
        if (this.investmentDao.existsById(id)) {
            investment.setId(id);
            this.investmentDao.save(investment);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (this.investmentDao.existsById(id)) {
            this.investmentDao.deleteById(id);
            return true;
        }
        return false;
    }
}
