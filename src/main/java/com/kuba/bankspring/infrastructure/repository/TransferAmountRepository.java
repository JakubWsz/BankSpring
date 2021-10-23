package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.TransferAmount;
import com.kuba.bankspring.entity.TransferSelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferAmountRepository extends JpaRepository<TransferAmount, Long> {
    TransferSelf saveTransferAmount(TransferAmount transferAmount);
}
