package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TransferSelf extends BaseEntity{
    private Account account;
    private TransferAmount transferAmount;
    private BigDecimal balanceBefore;
    private LocalDateTime createdAt;

    public TransferSelf(Account account, TransferAmount transferAmount, BigDecimal balanceBefore,
                        LocalDateTime createdAt) {
        this.account = account;
        this.transferAmount = transferAmount;
        this.balanceBefore = balanceBefore;
        this.createdAt = createdAt;
    }

    public TransferSelf() {}

    public Account getAccount() {
        return account;
    }

    public TransferAmount getTransferAmount() {
        return transferAmount;
    }

    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
