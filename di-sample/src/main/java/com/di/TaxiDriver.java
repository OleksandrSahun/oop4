package com.di;

import com.google.inject.Inject;
import java.util.Random;

public class TaxiDriver extends AbstractPerson implements RideInterface {
    private Car car;
    private String currentRoute;
    private double distance;
    private RideService rideService;

    private static final double BASE_FARE = 10.0;
    private static final double FARE_PER_KM = 5.0;

    @Inject
    public TaxiDriver(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    @Inject
    public void setCar(Car car) {
        this.car = car;
    }

    @Inject
    public void setRideService(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void buildRoute(String currentLocation, String destination) {
        currentRoute = currentLocation + " -> " + destination;
        System.out.println(getName() + " будує маршрут: " + currentRoute + ".");
    }

    @Override
    public double calculateRideCost(double distance) {
        return BASE_FARE + (FARE_PER_KM * distance);
    }

    @Override
    public void markComplete() {
        System.out.println(getName() + " завершив поїздку за маршрутом: " + currentRoute + ".");
        currentRoute = null;
    }

    public void acceptOrder(Client client, String currentLocation) {
        System.out.println(getName() + " прийняв замовлення від " + client.getName() + ".");
        buildRoute(currentLocation, client.getDestination());
        distance = new Random().nextInt(16) + 5;
        double cost = calculateRideCost(distance);
        System.out.println("Відстань: " + distance + " км. Вартість поїздки: " + cost + " грн.");

        if (client.payForRide(cost)) {
            rideService.saveRide(client.getName(), getName(), car.getModel(), cost);
            markComplete();
        } else {
            System.out.println("Поїздку не завершено через нестачу коштів.");
        }
    }

    @Override
    public boolean login(String phoneNumber) {
        if (getPhoneNumber().equals(phoneNumber)) {
            System.out.println(getName() + " увійшов у систему як водій.");
            return true;
        }
        System.out.println("Помилка входу.");
        return false;
    }
}
