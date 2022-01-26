package com.kuba.bankspring.domain.currency;

import com.kuba.bankspring.domain.account.AccountService;
import com.kuba.bankspring.domain.currency.exchange.CurrencyExchangeService;
import com.kuba.bankspring.entity.Account;
import com.kuba.bankspring.entity.CurrencyType;
import com.kuba.bankspring.entity.TransferAmount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyExchangeUserManager {
    private final AccountService accountService;
    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyExchangeUserManager(AccountService accountService, CurrencyExchangeService currencyExchangeService) {
        this.accountService = accountService;
        this.currencyExchangeService = currencyExchangeService;
    }

    public BigDecimal balanceCurrencyView(String accountNumber, CurrencyType currencyType) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
       // Balance balance = account.getBalance();
        TransferAmount transferAmount = new TransferAmount(account.getAmount(), account.getCurrencyType());
        return currencyExchangeService.currencyExchangeWithProvision(transferAmount, currencyType);
    }
}
