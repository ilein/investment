package com.gdtest.investment.controller;

import com.gdtest.investment.model.Bank;
import com.gdtest.investment.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Bank bank) {
        bankService.create(bank);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Bank>> read() {
        final List<Bank> banks = bankService.readAll();

        return banks != null && !banks.isEmpty()
                ? new ResponseEntity<>(banks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/sorted")
    public ResponseEntity<List<Bank>> readOrdered(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "asc") String direction,
                                                    @RequestParam(defaultValue = "") String search) {

        final List<Bank> banks = bankService.readAllFilter(pageNo, pageSize, sortBy, direction, search);

        return banks != null && !banks.isEmpty()
                ? new ResponseEntity<>(banks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Bank> read(@PathVariable(name = "id") int id) {
        final Optional<Bank> bank = bankService.read(id);

        return bank.isPresent()
                ? new ResponseEntity<>(bank.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/id/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id,
                                    @RequestBody Bank bank) {

        return bankService.update(bank, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = bankService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
