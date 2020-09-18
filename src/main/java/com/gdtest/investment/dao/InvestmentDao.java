package com.gdtest.investment.dao;

import com.gdtest.investment.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface InvestmentDao extends JpaRepository<Investment, Integer>, JpaSpecificationExecutor<Investment> {
}
