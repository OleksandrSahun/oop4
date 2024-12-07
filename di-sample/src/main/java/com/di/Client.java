package com.di;

import com.google.inject.Inject;

public class Client extends AbstractPerson {
    private final String destination;
    private double balance;

    @Inject
    public Client(String name, String phoneNumber, String destination, double balance) {
        super(name, phoneNumber);
        this.destination = destination;
        this.balance = balance;
    }

    public String getDestination() {
        return destination;
    }

    public void requestRide(TaxiDriver driver, String currentLocation) {
        System.out.println(getName() + " розмістив замовлення до " + destination + ".");
        driver.acceptOrder(this, currentLocation);
    }

    public boolean payForRide(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(getName() + " оплатив " + amount + " грн. Залишок: " + balance + " грн.");
            return true;
        } else {
            System.out.println(getName() + " не може оплатити поїздку. Недостатньо коштів.");
            return false;
        }
    }

    @Override
    public boolean login(String phoneNumber) {
        if (getPhoneNumber().equals(phoneNumber)) {
            System.out.println(getName() + " увійшов у систему як клієнт.");
            return true;
        }
        System.out.println("Помилка входу.");
        return false;
    }
}
