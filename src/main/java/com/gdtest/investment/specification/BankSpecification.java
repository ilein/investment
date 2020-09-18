package com.gdtest.investment.specification;

import com.gdtest.investment.model.Bank;
import org.springframework.data.jpa.domain.Specification;

public class BankSpecification {
    public static Specification<Bank> nameContain(String name) {
        return (bank, cq, cb) -> cb.like(bank.get("name"), "%" + name + "%");
    }

    public static Specification<Bank> bicContain(String bic) {
        return (bank, cq, cb) -> cb.like(bank.get("bic"), "%" + bic + "%");
    }
}
