package com.di;

import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RideService {
    private final Connection connection;

    @Inject
    public RideService(Connection connection) {
        this.connection = connection;
    }

    public void saveRide(String clientName, String driverName, String carModel, double price) {
        String sql = "INSERT INTO rides (client_name, driver_name, car_model, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, clientName);
            statement.setString(2, driverName);
            statement.setString(3, carModel);
            statement.setDouble(4, price);
            statement.executeUpdate();
            System.out.println("Поїздка збережена: " + clientName + ", " + driverName + ", " + carModel + ", " + price);
        } catch (SQLException e) {
            System.err.println("Помилка збереження поїздки: " + e.getMessage());
            throw new RuntimeException("Помилка збереження поїздки", e);
        }
    }
    
}
