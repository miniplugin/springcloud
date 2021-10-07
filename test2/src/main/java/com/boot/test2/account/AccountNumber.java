package com.boot.test2.account;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.Assert;

public class AccountNumber {

    private String accountNumber;

    public AccountNumber(String accountNumber) {
        Assert.notNull(accountNumber, "Account Number 공백일 수 없습니다.");
        Assert.isTrue(accountNumber.length() == 9,
                "Account Number 는 반드시 9자 이어야 합니다.");
        this.accountNumber = accountNumber;
    }

    @JsonValue
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AccountNumber))
            return false;

        AccountNumber that = (AccountNumber) o;

        return getAccountNumber() != null ? getAccountNumber().equals(
                that.getAccountNumber()) : that.getAccountNumber() == null;

    }

    @Override
    public int hashCode() {
        return getAccountNumber() != null ? getAccountNumber().hashCode() : 0;
    }
}