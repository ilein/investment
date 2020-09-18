package com.gdtest.investment.controller;

import com.gdtest.investment.model.Bank;
import com.gdtest.investment.model.Investment;
import com.gdtest.investment.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    InvestmentService investmentService;

    @Autowired
    public InvestmentService InvestmentController(InvestmentService investmentService) {
        return this.investmentService = investmentService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Investment investment) {
        investmentService.create(investment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Investment>> read() {
        final List<Investment> investments = investmentService.readAll();

        return investments != null && !investments.isEmpty()
                ? new ResponseEntity<>(investments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/sorted")
    public ResponseEntity<List<Investment>> readOrdered(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                  @RequestParam(defaultValue = "asc") String direction,
                                                  @RequestParam(defaultValue = "") String search) {

        final List<Investment> investments = investmentService.readAllFilter(pageNo, pageSize, sortBy, direction, search);

        return investments != null && !investments.isEmpty()
                ? new ResponseEntity<>(investments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Investment> read(@PathVariable(name = "id") int id) {
        final Optional<Investment> investment = investmentService.read(id);

        return investment.isPresent()
                ? new ResponseEntity<>(investment.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/id/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id,
                                    @RequestBody Investment investment) {

        return investmentService.update(investment, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = investmentService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
