package com.kuba.bankspring.entity;

import javax.persistence.Entity;

@Entity
public class Account extends BaseEntity {
    private AccountType accountType;
    private String accountNumber;
    private Balance balance;

    public Account() {
    }

    public Account( AccountType accountType, String accountNumber, Balance balance) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
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

    public void setBalance(Balance balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Account{" +
                ", accountType=" + accountType +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
