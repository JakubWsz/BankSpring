package com.kuba.bankspring.api.dto.request;

public class CreateUserRequest {
   private final String login;
   private final String password;
   private final String email;

    public CreateUserRequest(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
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
}
