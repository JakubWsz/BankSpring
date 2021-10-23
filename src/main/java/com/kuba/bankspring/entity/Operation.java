package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Operation extends BaseEntity{
    private LocalDateTime createdAt;
    private String balanceBefore;
    private String transferAmount;
    private String currencyType;
    private String message;

    public Operation(LocalDateTime createdAt, String balanceBefore, String transferAmount, String currencyType,
                     String message) {
        this.createdAt = createdAt;
        this.balanceBefore = balanceBefore;
        this.transferAmount = transferAmount;
        this.currencyType = currencyType;
        this.message = message;
    }

    public Operation() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getBalanceBefore() {
        return balanceBefore;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getMessage() {
        return message;
    }
}