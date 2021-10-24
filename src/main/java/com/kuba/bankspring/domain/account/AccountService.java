package com.kuba.bankspring.domain.account;

import com.kuba.bankspring.domain.user.UserService;
import com.kuba.bankspring.entity.*;
import com.kuba.bankspring.infrastructure.repository.AccountRepository;
import com.kuba.bankspring.infrastructure.repository.BalanceRepository;
import com.kuba.bankspring.infrastructure.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {
    private final UserService userService;
    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;
    private final ClientRepository clientRepository;

    public AccountService(UserService userService, AccountRepository accountRepository,
                          BalanceRepository balanceRepository, ClientRepository clientRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
        this.clientRepository = clientRepository;
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

        saveClient(firstName, lastName, idCardNumber);

        long clientId = getClientId(firstName, lastName, idCardNumber);

        Client client = getClient(clientId);

        saveBalance(currencyType, clientId);

        Balance balance = getBalance(idCardNumber,clientId);

        return accountRepository.saveAccount(new Account(accountType, accountNumber, client, user,balance, pin));
    }

    public void updateBalance(String accountNumber, BigDecimal sum) {
        accountRepository.updateBalance(accountNumber, sum);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("account not found"));
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

    private void saveBalance(CurrencyType currencyType, long clientId) {
        balanceRepository.saveBalance(new Balance(BigDecimal.ZERO, currencyType, clientId));
    }

    private Balance getBalance(String idCardNumber, long clientId){
       return balanceRepository.getBalanceByIdCardNumberAndClientId(idCardNumber, clientId);
    }

    private void saveClient(String firstName, String lastName, String idCardNumber) {
        clientRepository.saveClient(new Client(firstName, lastName, idCardNumber));
    }

    private long getClientId(String firstName, String lastName, String idCardNumber) {
        return clientRepository.getClientIdByFirstNameLastNameIdCardNumber(firstName, lastName, idCardNumber);
    }

    private Client getClient(long clientId){
        return clientRepository.getClientByClientId(clientId);
    }
}