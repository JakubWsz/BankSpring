package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Client extends BaseEntity{
    private String firstName;
    private String lastName;
    private String idCardNumber;
    @ManyToOne
    private  User user;
    @OneToOne
    private Account account;

    public Client(String firstName, String lastName, String idCardNumber, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCardNumber = idCardNumber;
        this.user = user;
    }

    public Client() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public User getUser() {
        return user;
    }

}