package com.example.pharmacymanagementsystem.Repository;

import com.example.pharmacymanagementsystem.Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockRepository {

    private static Connection connection;

    public StockRepository() throws SQLException {
        connection = DBConnection.connection();
    }

    // insert
    // read by id
    // read all
    // update
    public void update(int id, int quantity, int price) throws SQLException {
        final String sql = "UPDATE `stock` SET `quantity`=?, `price`=? WHERE `stock`.`drugId`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, price);
            statement.setInt(3, id);

            statement.executeUpdate();
        }
    }

    // delete
}
