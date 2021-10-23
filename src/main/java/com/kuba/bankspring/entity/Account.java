package com.kuba.bankspring.entity;

import javax.persistence.Entity;

@Entity
public class Account extends BaseEntity {
    private AccountType accountType;
    private String accountNumber;
    private Client client;
    private User user;
    private Balance balance;
    private Integer pin;

    public Account() {
    }

    public Account( AccountType accountType, String accountNumber, Client client, User user, Balance balance,
                   Integer pin) {
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

    public Integer getPin() {
        return pin;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }


    @Override
    public String toString() {
        return "Account{" +
                ", accountType=" + accountType +
                ", accountNumber='" + accountNumber + '\'' +
                ", client=" + client +
                ", user=" + user +
                ", balance=" + balance +
                ", pin=" + pin +
                '}';
    }
}
