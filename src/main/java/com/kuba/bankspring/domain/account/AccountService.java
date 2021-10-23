package com.kuba.bankspring.domain.account;

import com.kuba.bankspring.domain.user.UserService;
import com.kuba.bankspring.entity.*;
import com.kuba.bankspring.infrastructure.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {
    private final UserService userService;
    private final AccountRepository accountRepository;

    public AccountService(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String firstName, String lastName, String idCardNumber, long userId,
                                 AccountType accountType, CurrencyType currencyType, Integer pin) {
        User user = userService.getUserById(userId);
        boolean accountTypeExists = accountRepository.getAccountsByUserId(user.getId()).stream()
                .anyMatch(account -> account.getAccountType().equals(accountType));

        if (accountTypeExists) {
            throw new RuntimeException("provided account type already exists");
        }

        validateFirstnameLastnameIDCardNumber(firstName, lastName, idCardNumber, pin.toString());

        String accountNumber = UUID.randomUUID().toString();

        return accountRepository.saveAccount(new Account(accountType, accountNumber, new Client(firstName, lastName,
                idCardNumber), user, new Balance(BigDecimal.ZERO, currencyType),pin));
    }

    private void validateFirstnameLastnameIDCardNumber(String firstName, String lastName, String idCardNumber,
                                                       String pin) {
        if (firstName == null)
            throw new RuntimeException("you don't pass firstName");
        else if (lastName == null)
            throw new RuntimeException("you don't pass lastName");
        else if (idCardNumber == null)
            throw new RuntimeException("you don't pass idCardNumber");
        else if (pin == null)
            throw new RuntimeException("you don't pass pin");

        if (firstName.length() < 2)
            throw new RuntimeException("firstname is to short");
        else if (lastName.length() < 2)
            throw new RuntimeException("lastname is to short");
        else if (idCardNumber.length() > 9)
            throw new RuntimeException("ID Card Number length is to long");
        else if (idCardNumber.length() < 9)
            throw new RuntimeException("ID Card Number length is to short");

        if (pin.length() > 4)
            throw new RuntimeException("PIN is to long");
        else if (pin.length() < 4)
            throw new RuntimeException("PIN is to short");
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("account not found"));
    }

    public void updateBalance(String accountNumber, BigDecimal sum) {
        accountRepository.updateBalance(accountNumber, sum);
    }
}