package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Account extends BaseEntity {
    private AccountType accountType;
    private String accountNumber;
    //private Balance balance;
    private CurrencyType currencyType;
    private BigDecimal amount;

    //TODO rozbić BALANCE na kwotę i walutę. Do waluty zrobić dodatkowy konstruktor
    public Account() {
    }

//    public Account( AccountType accountType, String accountNumber, Balance balance) {
//        this.accountType = accountType;
//        this.accountNumber = accountNumber;
//       // this.balance = balance;
//    }


    public Account(AccountType accountType, String accountNumber, CurrencyType currencyType, BigDecimal amount) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.currencyType = currencyType;
        this.amount = amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

//    public Balance getBalance() {
//        return balance;
//    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

//    public void setBalance(Balance balance) {
//        this.balance = balance;
//    }


    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", accountType=" + accountType +
                ", accountNumber='" + accountNumber + '\'' +
               // ", balance=" + balance +
                '}';
    }
}
