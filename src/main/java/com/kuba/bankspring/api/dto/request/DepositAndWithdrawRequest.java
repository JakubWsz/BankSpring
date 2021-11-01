package com.kuba.bankspring.api.dto.request;

import com.kuba.bankspring.entity.TransferAmount;

public class DepositAndWithdrawRequest {
    private final TransferAmount transferAmount;
    private final String accountNumber;
    private final Integer pin;

    public DepositAndWithdrawRequest(TransferAmount transferAmount, String accountNumber, Integer pin) {
        this.transferAmount = transferAmount;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public TransferAmount getTransferAmount() {
        return transferAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getPin() {
        return pin;
    }
}
