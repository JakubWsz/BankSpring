package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TransferBetweenAccounts extends BaseEntity{
    private String accountNumber;
    private BigDecimal transferAmount;
    private CurrencyType currencyType;
    private BigDecimal balanceBefore;
    private LocalDateTime createdAt;

    public TransferBetweenAccounts(String accountNumber, BigDecimal transferAmount, CurrencyType currencyType,
                                   BigDecimal balanceBefore, LocalDateTime createdAt) {
        this.accountNumber = accountNumber;
        this.transferAmount = transferAmount;
        this.currencyType = currencyType;
        this.balanceBefore = balanceBefore;
        this.createdAt = createdAt;
    }

    public TransferBetweenAccounts() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(BigDecimal balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
