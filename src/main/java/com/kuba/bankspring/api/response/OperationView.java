package com.kuba.bankspring.api.response;

import java.time.LocalDateTime;

public class OperationView {
    private final LocalDateTime createdAt;
    private final String balanceBefore;
    private final String transferAmount;
    private final String currencyType;
    private final String message;

    public OperationView(LocalDateTime createdAt, String balanceBefore, String transferAmount, String currencyType,
                         String message) {
        this.createdAt = createdAt;
        this.balanceBefore = balanceBefore;
        this.transferAmount = transferAmount;
        this.currencyType = currencyType;
        this.message = message;
    }

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

    @Override
    public String toString() {
        return "OperationView{" +
                "createdAt=" + createdAt +
                ", balanceBefore='" + balanceBefore + '\'' +
                ", transferAmount='" + transferAmount + '\'' +
                ", currencyType='" + currencyType + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
