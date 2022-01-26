package com.kuba.bankspring.entity.account.types;

import com.kuba.bankspring.entity.Account;
import com.kuba.bankspring.entity.AccountType;
import com.kuba.bankspring.entity.CurrencyType;

import java.math.BigDecimal;

public class PersonalAccount extends Account {
    public PersonalAccount(AccountType accountType, String accountNumber, BigDecimal amount, CurrencyType currencyType) {
        super(accountType, accountNumber,currencyType ,amount);
    }
}
