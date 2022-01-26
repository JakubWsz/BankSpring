package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.TransferSelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferSelfRepository extends JpaRepository<TransferSelf, Long> {
    List<TransferSelf> getOperationsByAccountNumber(String accountNumber);
}
