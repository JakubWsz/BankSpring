package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TransferSelf extends BaseEntity{
    private String accountNumber;
    private TransferType transferType;
    private CurrencyType currencyType;
    private BigDecimal amount;
    private BigDecimal balanceBefore;
    private LocalDateTime createdAt;

    public TransferSelf(String accountNumber, TransferType transferType, CurrencyType currencyType,
                        BigDecimal amount, BigDecimal balanceBefore, LocalDateTime createdAt) {
        this.accountNumber = accountNumber;
        this.transferType = transferType;
        this.currencyType = currencyType;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.createdAt = createdAt;
    }

    public TransferSelf() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransferType getTransferSelfType() {
        return transferType;
    }

    public void setTransferSelfType(TransferType transferType) {
        this.transferType = transferType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBalanceBefore(BigDecimal balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
