package com.di;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Car {
    private final String model;
    private final String licenseNumber;

    @Inject
    public Car(@Named("CarModel") String model, @Named("LicenseNumber") String licenseNumber) {
        this.model = model;
        this.licenseNumber = licenseNumber;
    }

    public String getModel() {
        return model;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
