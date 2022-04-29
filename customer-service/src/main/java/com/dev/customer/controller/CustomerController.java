package com.dev.customer.controller;

import com.dev.customer.entity.Customer;
import com.dev.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public Mono<Customer> findById(@PathVariable("id") String id) {
        return customerService.findById(id);
    }

    @GetMapping
    public Flux<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}/with-accounts")
    public Mono<Customer> findByIdWithAccounts(@PathVariable("id") String id) {
        return customerService.findByIdWithAccounts(id);
    }

    @PostMapping
    public Mono<Customer> create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }
}
