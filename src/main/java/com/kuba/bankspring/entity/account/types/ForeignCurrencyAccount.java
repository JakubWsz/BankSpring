package com.kuba.bankspring.entity.account.types;

import com.kuba.bankspring.entity.Account;
import com.kuba.bankspring.entity.AccountType;
import com.kuba.bankspring.entity.CurrencyType;

import java.math.BigDecimal;

public class ForeignCurrencyAccount extends Account {
    public ForeignCurrencyAccount(AccountType accountType, String accountNumber, BigDecimal amount, CurrencyType currencyType)  {
        super(accountType, accountNumber, currencyType, amount);
    }
}
