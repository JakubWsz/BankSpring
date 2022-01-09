package com.kuba.bankspring.api.dto.request;

public class CreateUserRequest {
    private final String login;
    private final String password;
    private final String email;
    private final int pin;

    public CreateUserRequest(String login, String password, String email, int pin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.pin = pin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getPin() {
        return pin;
    }
}
