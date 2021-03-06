package com.kuba.bankspring.api.rest;

import com.kuba.bankspring.api.dto.request.CreateAccountRequest;
import com.kuba.bankspring.domain.account.AccountService;
import com.kuba.bankspring.entity.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return accountService.createAccount(
                createAccountRequest.getFirstName(),
                createAccountRequest.getLastName(),
                createAccountRequest.getEmail(),
                createAccountRequest.getAccountType(),
                createAccountRequest.getCurrencyType(),
                createAccountRequest.getPin()
        );
    }
}
