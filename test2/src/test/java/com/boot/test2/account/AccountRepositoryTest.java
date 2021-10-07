package com.boot.test2.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class AccountRepositoryTest {
    //AccountNumber 클래스는 숫자 널체크 및 개수 제한 조건을 줄수 있다.
    //스프링5 레커시에서는 @NotNull(message="사용자ID는 빈값으로 저장할 수 없습니다.") 이런식으로 널체크를 한다.
    private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber("123456789");

    @Autowired
    private AccountRepository accountRepository;

    @Autowired //위 레포지토리를 사용하지 않고, Persistence CRUD 를 관리
    private TestEntityManager entityManager;

    @Test
    public void findAccountsByUsernameTest() throws Exception {
        this.entityManager.persist(new Account("kimilguk", ACCOUNT_NUMBER));
        List<Account> account = this.accountRepository.findAccountsByUsername("kimilguk");
        assertThat(account.size()).isEqualTo(1);
        Account actual = account.get(0);
        assertThat(actual.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER.getAccountNumber());
        assertThat(actual.getUsername()).isEqualTo("kimilguk");
    }

    @Test
    public void findAccountByAccountNumberTest() throws Exception {
        //this.entityManager.persist(new Account("kimilguk", ACCOUNT_NUMBER));
        this.accountRepository.save(new Account("kimilguk", ACCOUNT_NUMBER));
        Account account = this.accountRepository
                .findAccountByAccountNumber(ACCOUNT_NUMBER.getAccountNumber());
        assertThat(account).isNotNull();
        assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER.getAccountNumber());
    }

    @Test //레포지토리를 사용한 Persistence CRUD
    public void findAccountByAccountNumberTest2() throws Exception {
        this.accountRepository.save(new Account("kimilguk", ACCOUNT_NUMBER));
        Account account = this.accountRepository
                .findAccountByAccountNumber("000000000");
        assertThat(account).isNull();
    }
}