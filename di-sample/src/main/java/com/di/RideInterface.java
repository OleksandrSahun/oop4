package com.di;

public interface RideInterface {
    void buildRoute(String currentLocation, String destination);
    double calculateRideCost(double distance);
    void markComplete();
}