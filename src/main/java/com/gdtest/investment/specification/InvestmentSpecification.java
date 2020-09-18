package com.gdtest.investment.specification;

import com.gdtest.investment.model.Investment;
import org.springframework.data.jpa.domain.Specification;

public class InvestmentSpecification {
    public static Specification<Investment> clientNameContain(String clientName) {
        return (investment, cq, cb) -> (cb.like(investment.get("client").get("name"), "%" + clientName + "%"));
    }

    public static Specification<Investment> bankNameContain(String bankName) {
        return (investment, cq, cb) -> (cb.like(investment.get("bank").get("name"), "%" + bankName + "%"));
    }
}
