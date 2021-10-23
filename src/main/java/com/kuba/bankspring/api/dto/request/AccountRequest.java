package com.kuba.bankspring.api.dto.request;

import com.kuba.bankspring.entity.AccountType;
import com.kuba.bankspring.entity.Balance;
import com.kuba.bankspring.entity.Client;
import com.kuba.bankspring.entity.User;

public class AccountRequest {
    private final AccountType accountType;
    private final String accountNumber;
    private final Client client;
    private final User user;
    private final Balance balance;
    private final Integer pin;

    public AccountRequest(AccountType accountType, String accountNumber, Client client, User user, Balance balance, Integer pin) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.client = client;
        this.user = user;
        this.balance = balance;
        this.pin = pin;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Client getClient() {
        return client;
    }

    public User getUser() {
        return user;
    }

    public Balance getBalance() {
        return balance;
    }

    public Integer getPin() {
        return pin;
    }
}
