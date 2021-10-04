package com.boot.test2.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/account")
    public ResponseEntity getUserAccount() throws Exception {
        return Optional.ofNullable(accountService.getUserAccounts())
                .map(a -> new ResponseEntity<List<Account>>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Account 사용자가 존재하지 않습니다."));
    }
}