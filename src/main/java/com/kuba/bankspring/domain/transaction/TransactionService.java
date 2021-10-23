package com.kuba.bankspring.domain.transaction;

import com.kuba.bankspring.api.dto.response.OperationView;
import com.kuba.bankspring.domain.account.AccountService;
import com.kuba.bankspring.entity.*;
import com.kuba.bankspring.infrastructure.repository.TransferBetweenAccountsRepository;
import com.kuba.bankspring.infrastructure.repository.TransferSelfRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final AccountService accountService;
    private final TransferSelfRepository transferSelfRepository;
    private final TransferBetweenAccountsRepository transferBetweenAccountsRepository;

    public TransactionService(AccountService accountService, TransferSelfRepository transferSelfRepository,
                              TransferBetweenAccountsRepository transferBetweenAccountsRepository) {
        this.accountService = accountService;
        this.transferSelfRepository = transferSelfRepository;
        this.transferBetweenAccountsRepository = transferBetweenAccountsRepository;
    }


    public Account deposit(TransferAmount transferAmount, String accountNumber, Integer pin) {
        validateDepositAndWithdrawInput(transferAmount, accountNumber);
        Account account = accountService.getAccountByAccountNumber(accountNumber);

       if (!pin.equals(account.getPin())){
           throw new RuntimeException("Wrong PIN passed");
       }

        if (transferAmount.getCurrencyType() != account.getBalance().getCurrencyType()) {
            throw new UnsupportedOperationException("different currency than account is unsupported yet");
        }
        BigDecimal sum = account.getBalance().getAmount().add(transferAmount.getAmount());
        account.getBalance().setAmount(sum);
        accountService.updateBalance(accountNumber, sum);
        transferSelfRepository.saveTransferSelf(new TransferSelf(account, transferAmount,
                sum.subtract(transferAmount.getAmount()), LocalDateTime.now()));
        return account;
    }

    public Account withdraw(TransferAmount transferAmount, String accountNumber, Integer pin) {
        validateDepositAndWithdrawInput(transferAmount, accountNumber);
        Account account = accountService.getAccountByAccountNumber(accountNumber);

        if (!pin.equals(account.getPin())){
            throw new RuntimeException("Wrong PIN passed");
        }

        if (transferAmount.getCurrencyType() != account.getBalance().getCurrencyType()) {
            throw new UnsupportedOperationException("different currency than account is unsupported yet");
        }
        BigDecimal sum = account.getBalance().getAmount().subtract(transferAmount.getAmount());
        account.getBalance().setAmount(sum);
        accountService.updateBalance(accountNumber, sum);
        transferSelfRepository.saveTransferSelf(new TransferSelf(account, transferAmount,
                sum.add(transferAmount.getAmount()), LocalDateTime.now()));
        return account;
    }

    public List<OperationView> getHistory(String accountNumber, Integer pin) {
        List<TransferSelf> historyTransferSelf = transferSelfRepository.getOperationsByAccountNumber(accountNumber);
        List<TransferBetweenAccounts> historyTransferBetween =
                transferBetweenAccountsRepository.getOperationsByAccountNumberAndPin(accountNumber,pin);
        List<OperationView> operations = new ArrayList<>();

        operations.addAll(mapToOperationsSelf(historyTransferSelf).stream()
                .map(operation -> new OperationView(operation.getCreatedAt(),operation.getBalanceBefore(),
                        operation.getTransferAmount(),operation.getCurrencyType(),operation.getMessage()))
                .collect(Collectors.toList()));

        operations.addAll(mapToOperationsBetweenAccounts(historyTransferBetween).stream()
                .map(operation -> new OperationView(operation.getCreatedAt(),operation.getBalanceBefore(),
                        operation.getTransferAmount(),operation.getCurrencyType(),operation.getMessage()))
                .collect(Collectors.toList()));
        operations.sort(Comparator.comparing(OperationView::getCreatedAt));
        return operations;
    }

    private List<Operation> mapToOperationsSelf(List<TransferSelf> historyTransferSelf) {
        List<Operation> operations = new ArrayList<>();
        for (TransferSelf transferSelf : historyTransferSelf) {
            Operation operation = new Operation(
                    transferSelf.getCreatedAt(),
                    transferSelf.getBalanceBefore().toPlainString(),
                    transferSelf.getTransferAmount().getAmount().toPlainString(),
                    transferSelf.getTransferAmount().getCurrencyType().toString(),
                    "deposit or withdraw " + transferSelf.getTransferAmount().getAmount().toPlainString()
                            + " at " + transferSelf.getCreatedAt());
            operations.add(operation);
        }
        return operations;
    }

    private List<Operation> mapToOperationsBetweenAccounts(List<TransferBetweenAccounts> historyTransferSelf) {
        List<Operation> operations = new ArrayList<>();
        for (TransferBetweenAccounts transferBetweenAccounts : historyTransferSelf) {
            Operation operation = new Operation(
                    transferBetweenAccounts.getCreatedAt(),
                    transferBetweenAccounts.getBalanceBefore().toPlainString(),
                    transferBetweenAccounts.getTransferAmount().getAmount().toPlainString(),
                    transferBetweenAccounts.getTransferAmount().getCurrencyType().toString(),
                    "deposit or withdraw " + transferBetweenAccounts
                            .getTransferAmount().getAmount().toPlainString()
                            + " at " + transferBetweenAccounts.getCreatedAt());
            operations.add(operation);
        }
        return operations;
    }

    public Account transfer(TransferAmount transferAmount, String myAccountNumber, String targetAccountNumber
            , Integer pin) {
        validateTransferInput(transferAmount, myAccountNumber, targetAccountNumber);
        Account myAccount = accountService.getAccountByAccountNumber(myAccountNumber);
        Account targetAccount = accountService.getAccountByAccountNumber(targetAccountNumber);

        if (!pin.equals(myAccount.getPin())){
            throw new RuntimeException("Wrong PIN passed");
        }
        if (transferAmount.getCurrencyType() != myAccount.getBalance().getCurrencyType()) {
            throw new UnsupportedOperationException("different currency than account is unsupported yet");
        }
        if (transferAmount.getCurrencyType() != targetAccount.getBalance().getCurrencyType()) {
            throw new UnsupportedOperationException("different currency than account is unsupported yet");
        }
        BigDecimal mySum = myAccount.getBalance().getAmount().subtract(transferAmount.getAmount());
        myAccount.getBalance().setAmount(mySum);
        BigDecimal targetSum = targetAccount.getBalance().getAmount().add(transferAmount.getAmount());
        targetAccount.getBalance().setAmount(targetSum);
        accountService.updateBalance(myAccountNumber, mySum);
        accountService.updateBalance(targetAccountNumber, targetSum);
        transferBetweenAccountsRepository.saveTransferBetweenAccountsRepository(
                new TransferBetweenAccounts(myAccount,transferAmount,
                        mySum.add(transferAmount.getAmount()), LocalDateTime.now()));
        transferBetweenAccountsRepository.saveTransferBetweenAccountsRepository(
                new TransferBetweenAccounts(targetAccount
                        , transferAmount, targetSum.add(transferAmount.getAmount()),
                        LocalDateTime.now()));
        return myAccount;
    }

    private void validateDepositAndWithdrawInput(TransferAmount transferAmount, String accountNumber) {

        if (transferAmount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("operation disallowed");
        }
        if (accountNumber == null) {
            throw new RuntimeException("passed account number is null");
        }
    }

    private void validateTransferInput(TransferAmount transferAmount, String myAccountNumber,
                                       String targetAccountNumber) {
        if (transferAmount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("operation disallowed");
        }
        if (myAccountNumber == null) {
            throw new RuntimeException("your account number is null");
        }
        if (targetAccountNumber == null) {
            throw new RuntimeException("target account number is null");
        }
    }
}
