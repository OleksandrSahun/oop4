package com.di;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TaxiServiceProgram {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TaxiServiceModule());

        TaxiDriver driver = injector.getInstance(TaxiDriver.class);
        Client client = injector.getInstance(Client.class);

        if (!driver.login("098-765-4321")) return;
        if (!client.login("123-456-7890")) return;

        client.requestRide(driver, "Таймс-Сквер");
    }
}