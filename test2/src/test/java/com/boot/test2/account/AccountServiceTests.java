package com.boot.test2.account;

import com.boot.test2.users.Users;
import com.boot.test2.users.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AccountServiceTests {

    @MockBean//스프링 빈을 모의로 사용할때 지정
    private UsersService usersService;

    @MockBean
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    public void before() {
        accountService = new AccountService(accountRepository, usersService); // <2>
    }

    @Test
    public void getUserAccountsReturnsSingleAccount() throws Exception {
        given(this.accountRepository.findAccountsByUsername("user")).willReturn(
                Collections
                        .singletonList(new Account("user", new AccountNumber("123456789")))); // <3>

        given(this.usersService.getAuthenticatedUser()).willReturn(
                new Users(0L, "user", "John", "Doe")); // <4>

        List<Account> actual = accountService.getUserAccounts();// <5>

        assertThat(actual).size().isEqualTo(1);
        assertThat(actual.get(0).getUsername()).isEqualTo("user");
        assertThat(actual.get(0).getAccountNumber()).isEqualTo("123456789");
    }
}