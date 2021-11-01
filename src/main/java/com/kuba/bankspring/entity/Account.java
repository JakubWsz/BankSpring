package com.kuba.bankspring.entity;

import javax.persistence.Entity;

@Entity
public class Account extends BaseEntity {
    private AccountType accountType;
    private String accountNumber;
    private Balance balance;
    private Integer pin;

    public Account() {
    }

    public Account( AccountType accountType, String accountNumber, Balance balance, Integer pin) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
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
                ", balance=" + balance +
                ", pin=" + pin +
                '}';
    }
}
