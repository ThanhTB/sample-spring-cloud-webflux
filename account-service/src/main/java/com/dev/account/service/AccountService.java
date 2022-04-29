package com.dev.account.service;

import com.dev.account.entity.Account;
import com.dev.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Flux findByCustomerId(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public Flux findAll() {
        return accountRepository.findAll();
    }

    public Mono findById(String id) {
        return accountRepository.findById(id);
    }

    public Mono save(Account account) {
        return accountRepository.save(account);
    }
}
