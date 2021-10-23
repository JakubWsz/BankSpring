package com.kuba.bankspring.entity;

import javax.persistence.Entity;

@Entity
public class Client extends BaseEntity{
    private String firstName;
    private String lastName;
    private String idCardNumber;


    public Client(String firstName, String lastName, String idCardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCardNumber = idCardNumber;
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
}