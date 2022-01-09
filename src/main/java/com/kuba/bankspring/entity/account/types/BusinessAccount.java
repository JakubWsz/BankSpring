package com.kuba.bankspring.entity.account.types;

import com.kuba.bankspring.entity.Account;
import com.kuba.bankspring.entity.AccountType;
import com.kuba.bankspring.entity.Balance;
import com.kuba.bankspring.infrastructure.factory.AccountFactory;

public class BusinessAccount extends Account {
    public BusinessAccount(AccountType accountType, String accountNumber, Balance balance) {
        super(accountType, accountNumber, balance);
    }
}
