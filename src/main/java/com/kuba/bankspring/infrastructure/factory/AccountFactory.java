package com.kuba.bankspring.infrastructure.factory;

import com.kuba.bankspring.entity.Account;
import com.kuba.bankspring.entity.AccountType;
import com.kuba.bankspring.entity.CurrencyType;
import com.kuba.bankspring.entity.account.types.BusinessAccount;
import com.kuba.bankspring.entity.account.types.ForeignCurrencyAccount;
import com.kuba.bankspring.entity.account.types.PersonalAccount;
import com.kuba.bankspring.entity.account.types.SavingsAccount;

import java.math.BigDecimal;

public class AccountFactory {
    public static Account accountCreator(AccountType accountType, String accountNumber, BigDecimal amount,
                                         CurrencyType currencyType){
        if (accountType == AccountType.PERSONAL_1)
            return new PersonalAccount(accountType,accountNumber,amount, currencyType);
        else if (accountType == AccountType.BUSINESS_2)
            return new BusinessAccount(accountType,accountNumber,amount, currencyType);
        else if (accountType == AccountType.FOREIGN_CURRENCY_3)
            return new ForeignCurrencyAccount(accountType,accountNumber,amount, currencyType);
        else if (accountType == AccountType.SAVINGS_4)
            return new SavingsAccount(accountType,accountNumber,amount, currencyType);
        else return new Account();
    }
}
