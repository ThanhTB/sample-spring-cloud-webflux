package com.dev.customer.service;

import com.dev.customer.entity.Account;
import com.dev.customer.entity.Customer;
import com.dev.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    WebClient.Builder webClientBuilder;

    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Mono<Customer> create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Mono<Customer> findByIdWithAccounts(String id) {
        try {
            Flux<Account> accounts = webClientBuilder
                    .build()
                    .get()
                    .uri("http://account-service/accounts/customers/{customerId}", id)
                    .retrieve()
                    .bodyToFlux(Account.class);

            return accounts
                    .collectList()
                    .map(account -> customerRepository.findById(id).doOnNext(c -> c.setAccounts(account)))
                    .flatMap(c -> c);
        } catch (Exception exception) {

            throw new RuntimeException(exception.getMessage());
        }
    }
}
