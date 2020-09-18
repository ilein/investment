package com.gdtest.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.activation.DataSource;

@SpringBootApplication
public class InvestmentApplication {
    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(InvestmentApplication.class, args);
    }

}
