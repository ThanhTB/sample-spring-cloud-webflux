package com.dev.account.controller;

import com.dev.account.entity.Account;
import com.dev.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("customers/{customerId}")
    public Flux findByCustomer(@PathVariable("customerId") String customerId) {
        return accountService.findByCustomerId(customerId);
    }

    @GetMapping
    public Flux findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono findById(@PathVariable("id") String id) {
        return accountService.findById(id);
    }

    @PostMapping
    public Mono create(@RequestBody Account account) {
        return accountService.save(account);
    }
}
