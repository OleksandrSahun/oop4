package com.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TaxiServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("CarModel")).toInstance("Toyota Camry");
        bind(String.class).annotatedWith(Names.named("LicenseNumber")).toInstance("XYZ123");
    }

    @Provides
    @Singleton
    public Connection provideConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:target/taxi_service.db");
            createTableIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалося створити з'єднання з базою даних", e);
        }
    }

    private void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS rides (" +
                                "ride_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "client_name TEXT NOT NULL, " +
                                "driver_name TEXT NOT NULL, " +
                                "car_model TEXT NOT NULL, " +
                                "price REAL NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Помилка створення таблиці", e);
        }
    }

    @Provides
    public Client provideClient() {
        return new Client("Джон Доу", "123-456-7890", "Центральний парк", 70.00);
    }

    @Provides
    public TaxiDriver provideTaxiDriver(Car car, RideService rideService) {
        TaxiDriver driver = new TaxiDriver("Аліса Сміт", "098-765-4321");
        driver.setCar(car); 
        driver.setRideService(rideService); 
        return driver;
    }
}
