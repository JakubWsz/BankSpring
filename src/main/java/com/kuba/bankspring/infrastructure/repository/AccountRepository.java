package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean isAccountExists(Long id);
    Account saveAccount(Account account);
    List<Account> getAccountsByUserId(long userId);
    Optional<Account> getByAccountNumber(String accountNumber);
    void updateBalance(String accountNumber, BigDecimal amount);
}
