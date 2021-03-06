package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance saveBalance(Balance balance);
}
