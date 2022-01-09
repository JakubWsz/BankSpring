package com.kuba.bankspring.entity;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity{
    private String login;
    private String password;
    private String email;
    private Integer pin;

    public User() {
    }

    public User(String login, String password, String email, Integer pin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.pin = pin;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}