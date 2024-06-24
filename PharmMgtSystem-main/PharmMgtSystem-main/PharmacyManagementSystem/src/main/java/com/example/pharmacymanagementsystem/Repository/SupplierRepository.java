package com.example.pharmacymanagementsystem.Repository;

import com.example.pharmacymanagementsystem.Database.DBConnection;
import com.example.pharmacymanagementsystem.Mapper.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {
    private static Connection connection;

    public SupplierRepository() throws SQLException {
        connection = DBConnection.connection();
    }

    // insert
    public void insert(String name, String address, String phoneNumber, String email) throws SQLException {
        final String sql = "INSERT INTO `supplier`(`name`, `address`, `phoneNumber`, `email`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phoneNumber);
            statement.setString(4, email);

            statement.executeUpdate();
        }
    }

    public void insert(Supplier supplier) throws SQLException {
        final String sql = "INSERT INTO `supplier`(`name`, `address`, `phoneNumber`, `email`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getAddress());
            statement.setString(3, supplier.getPhoneNumber());
            statement.setString(4, supplier.getEmail());

            statement.executeUpdate();
        }
    }

    // read by id
    public Supplier read(int id) throws SQLException {
        final String sql = "SELECT `id`, `name`, `address`, `phoneNumber`, `email` FROM `supplier` WHERE `id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int _id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");

                return new Supplier(_id, name, address, phoneNumber, email);
            }
        }

        throw new SQLException("It seems there is no record with such id");
    }

    // read all
    public List<Supplier> read() throws SQLException {
        final String sql = "SELECT `id`, `name`, `address`, `phoneNumber`, `email` FROM `supplier`";
        List<Supplier> suppliers = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int _id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");

                suppliers.add(new Supplier(_id, name, address, phoneNumber, email));
            }
        }

        return suppliers;
    }

    // update
    public void update(int id, String name, String address, String phoneNumber, String email) throws SQLException {
        final String sql = "UPDATE `supplier` SET `name`=?, `address`=?, `phoneNumber`=?, `email`=?  WHERE `id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phoneNumber);
            statement.setString(4, email);
            statement.setInt(5, id);

            statement.executeUpdate();
        }
    }

    // delete
    public void delete(int id) throws SQLException {
        final String sql = "DELETE FROM `supplier` WHERE `id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }
}
