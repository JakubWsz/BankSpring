package com.kuba.bankspring.api.dto.response;

import com.kuba.bankspring.entity.AccountType;
import com.kuba.bankspring.entity.Balance;
import com.kuba.bankspring.entity.Client;
import com.kuba.bankspring.entity.User;

public class UserDetailsView {
    private final Long id;
    private final String login;
    private final String email;
    private final AccountType accountType;
    private final String accountNumber;
    private final Client client;
    private final User user;
    private final Balance balance;

    public UserDetailsView(Long id, String login, String email, AccountType accountType,
                           String accountNumber, Client client, User user, Balance balance) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.client = client;
        this.user = user;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserDetailsView{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", accountType=" + accountType +
                ", accountNumber='" + accountNumber + '\'' +
                ", client=" + client +
                ", user=" + user +
                ", balance=" + balance +
                '}';
    }
}
