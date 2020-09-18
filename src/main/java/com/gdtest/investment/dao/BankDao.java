package com.gdtest.investment.dao;

import com.gdtest.investment.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDao extends JpaRepository<Bank, Integer>, JpaSpecificationExecutor<Bank> {

}
