package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.TransferBetweenAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferBetweenAccountsRepository extends JpaRepository<TransferBetweenAccounts, Long> {
    List<TransferBetweenAccounts> findTransferBetweenAccountsByAccountNumber(String accountNumber);
}
