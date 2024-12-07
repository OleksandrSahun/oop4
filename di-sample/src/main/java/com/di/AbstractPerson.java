package com.di;

public abstract class AbstractPerson {
    private String name;
    private String phoneNumber;

    public AbstractPerson(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public abstract boolean login(String phoneNumber);
}