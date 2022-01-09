package com.kuba.bankspring.entity.account.types;

import com.kuba.bankspring.entity.Account;
import com.kuba.bankspring.entity.AccountType;
import com.kuba.bankspring.entity.Balance;

public class PersonalAccount extends Account {
    public PersonalAccount(AccountType accountType, String accountNumber, Balance balance) {
        super(accountType, accountNumber, balance);
    }
}
