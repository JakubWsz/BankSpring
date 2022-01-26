package com.kuba.bankspring.domain.account;

import com.kuba.bankspring.entity.*;
import com.kuba.bankspring.infrastructure.factory.AccountFactory;
import com.kuba.bankspring.infrastructure.repository.AccountRepository;
import com.kuba.bankspring.infrastructure.repository.ClientRepository;
import com.kuba.bankspring.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountService(UserRepository userRepository, AccountRepository accountRepository,
                          ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public Account createAccount(String firstName, String lastName, String email, AccountType accountType,
                                 CurrencyType currencyType) {
        String accountNumber = UUID.randomUUID().toString();

        Client client = getClient(firstName, lastName);

        User user = getUser(email);

        Account account = AccountFactory.accountCreator(accountType, accountNumber,BigDecimal.ZERO ,currencyType);

        if (client.getUser().equals(user))
            return accountRepository.save(account);
        else throw new RuntimeException("Firstname, lastname or email is invalid");
    }

    public void updateBalance(String accountNumber, BigDecimal sum) {
        accountRepository.findAccountByAccountNumber(accountNumber)
                .ifPresent(account -> {
                    account.setAmount(sum);
                    accountRepository.save(account);});
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("account not found"));
    }

    private void validatePin(String pin) {

        if (pin.length() > 4)
            throw new RuntimeException("PIN is to long");
        else if (pin.length() < 4)
            throw new RuntimeException("PIN is to short");
    }

    private Client getClient(String firstName, String lastName) {
        return clientRepository.getClientByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new RuntimeException("There is no client with passed firstname and lastname "));
    }

    private User getUser(String email) {
        return userRepository.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("There is no user with passed email"));
    }
}