package com.example.pharmacymanagementsystem.Repository;

import com.example.pharmacymanagementsystem.Database.DBConnection;
import com.example.pharmacymanagementsystem.Mapper.Buyer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerRepository {
    private static Connection connection;

    public BuyerRepository() throws SQLException {
        connection = DBConnection.connection();
    }

    // insert
    public void insert(String name, String phoneNumber, String address) throws SQLException {
        final String sql = "INSERT INTO `buyer`(`name`, `phoneNumber`, `address`) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, address);

            statement.executeUpdate();
        }
    }

    public void insert(Buyer buyer) throws SQLException {
        final String sql = "INSERT INTO `buyer`(`name`, `phoneNumber`, `address`) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, buyer.getName());
            statement.setString(2, buyer.getPhoneNumber());
            statement.setString(3, buyer.getAddress());

            statement.executeUpdate();
        }
    }

    // read by id
    public Buyer read(int id) throws SQLException {
        final String sql = "SELECT `id`, `name`, `phoneNumber`, `address` FROM `buyer` WHERE `id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int _id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");

                return new Buyer(_id, name, phoneNumber, address);
            }
        }

        throw new SQLException("It seems there is no record with such id");
    }

    // read all
    public List<Buyer> read() throws SQLException {
        final String sql = "SELECT `id`, `name`, `phoneNumber`, `address` FROM `buyer`";
        List<Buyer> buyers = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int _id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");

                buyers.add(new Buyer(_id, name, phoneNumber, address));
            }
        }

        return buyers;
    }

    // update
    public void update(int id, String name, String phoneNumber, String address) throws SQLException {
        final String sql = "UPDATE `buyer` SET `name`=?, `phoneNumber`=?,`address`=? WHERE `id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, address);
            statement.setInt(4, id);

            statement.executeUpdate();
        }
    }

    // delete
}
