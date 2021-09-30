package com.boot.test2.account;

import com.boot.test2.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final UsersService usersService; // <1>

    @Autowired
    public AccountService(AccountRepository ar, UsersService us) { // <2>
        this.accountRepository = ar;
        this.usersService = us;
    }

    public List<Account> getUserAccounts() {
        // <3>
        return Optional.ofNullable(usersService.getAuthenticatedUser())
                .map(u -> accountRepository.findAccountsByUsername(u.getUsername()))
                .orElse(Collections.emptyList());
    }
}