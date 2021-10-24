package com.kuba.bankspring.api.dto.request;

import com.kuba.bankspring.entity.*;

public class CreateAccountRequest {

    private final String firstName;
    private final String lastName;
    private final String idCardNumber;
    private final User user;
    private final AccountType accountType;
    private final CurrencyType currencyType;
    private final Integer pin;

    public CreateAccountRequest(String firstName, String lastName, String idCardNumber, User user,
                                AccountType accountType, CurrencyType currencyType, Integer pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCardNumber = idCardNumber;
        this.user = user;
        this.accountType = accountType;
        this.currencyType = currencyType;
        this.pin = pin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public User getUser() {
        return user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public Integer getPin() {
        return pin;
    }
}
