package com.example.pharmacymanagementsystem.Repository;

import com.example.pharmacymanagementsystem.Database.DBConnection;
import com.example.pharmacymanagementsystem.Mapper.Drug;
import com.example.pharmacymanagementsystem.Mapper.Stock;
import com.example.pharmacymanagementsystem.Mapper.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugRepository {
    private static Connection connection;

    public DrugRepository() throws SQLException {
        connection = DBConnection.connection();
    }

    // insert
    public void insert(
            String name,
            String genericName,
            String dosage,
            String strength,
            int supplierId,
            int quantity,
            int price) throws SQLException {
        final String sqlDrug = "INSERT INTO `drug`(`name`, `genericName`, `dosage`, `strength`, `supplierId`) " +
                "VALUES (?, ?, ?, ?, ?)";

        final String sqlStock = "INSERT INTO `stock`(`drugId`, `quantity`, `price`) " +
                "VALUES ((SELECT `drug`.`id` FROM `drug` WHERE `drug`.`name`=? LIMIT 1), ?, ?)";

        try (PreparedStatement statementDrug = connection.prepareStatement(sqlDrug)) {
            statementDrug.setString(1, name);
            statementDrug.setString(2, genericName);
            statementDrug.setString(3, dosage);
            statementDrug.setString(4, strength);
            statementDrug.setInt(5, supplierId);

            statementDrug.executeUpdate();
        }

        try (PreparedStatement statementStock = connection.prepareStatement(sqlStock)) {
            statementStock.setString(1, name);
            statementStock.setInt(2, quantity);
            statementStock.setInt(3, price);

            statementStock.executeUpdate();
        }
    }

    // read by id
    public Drug read(int id) throws SQLException {
        final String sql = "SELECT " +
                "`drug`.`id` as drugId, " +
                "`drug`.`name` AS drugName, " +
                "`drug`.`genericName` AS drugGenericName, " +
                "`drug`.`dosage` AS drugDosage, " +
                "`drug`.`strength` AS drugStrength, " +
                "`supplier`.`id` AS supplierId, " +
                "`supplier`.`name` AS supplierName, " +
                "`supplier`.`address` AS supplierAddress, " +
                "`supplier`.`phoneNumber` AS supplierPhoneNumber, " +
                "`supplier`.`email` AS supplierEmail, " +
                "`stock`.`quantity` AS stockQuantity, " +
                "`stock`.`price` AS stockPrice " +
                " FROM `drug` " +
                "INNER JOIN `supplier` ON `drug`.`supplierId`=`supplier`.`id` " +
                "INNER JOIN `stock` ON `drug`.`id`=`stock`.`drugId` " +
                "WHERE `drug`.`id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int drugId = resultSet.getInt("drugId");
                String drugName = resultSet.getString("drugName");
                String drugGenericName = resultSet.getString("drugGenericName");
                String drugDosage = resultSet.getString("drugDosage");
                String drugStrength = resultSet.getString("drugStrength");

                int supplierId = resultSet.getInt("supplierId");
                String supplierName = resultSet.getString("supplierName");
                String supplierAddress = resultSet.getString("supplierAddress");
                String supplierPhoneNumber = resultSet.getString("supplierPhoneNumber");
                String supplierEmail = resultSet.getString("supplierEmail");

                int stockQuantity = resultSet.getInt("stockQuantity");
                int stockPrice = resultSet.getInt("stockPrice");

                return new Drug(
                        drugId,
                        drugName,
                        drugGenericName,
                        drugDosage,
                        drugStrength,
                        new Supplier(
                                supplierId,
                                supplierName,
                                supplierAddress,
                                supplierPhoneNumber,
                                supplierEmail
                        ),
                        new Stock(
                                stockPrice,
                                stockQuantity
                        )
                );
            }
        }

        throw new SQLException("It seems there is no record with such id");
    }


    // read all
    public List<Drug> read() throws SQLException {
        final String sql = "SELECT " +
                "`drug`.`id` as drugId, " +
                "`drug`.`name` AS drugName, " +
                "`drug`.`genericName` AS drugGenericName, " +
                "`drug`.`dosage` AS drugDosage, " +
                "`drug`.`strength` AS drugStrength, " +
                "`supplier`.`id` AS supplierId, " +
                "`supplier`.`name` AS supplierName, " +
                "`supplier`.`address` AS supplierAddress, " +
                "`supplier`.`phoneNumber` AS supplierPhoneNumber, " +
                "`supplier`.`email` AS supplierEmail, " +
                "`stock`.`quantity` AS stockQuantity, " +
                "`stock`.`price` AS stockPrice " +
                " FROM `drug` " +
                "INNER JOIN `supplier` ON `drug`.`supplierId`=`supplier`.`id` " +
                "INNER JOIN `stock` ON `drug`.`id`=`stock`.`drugId`";


        List<Drug> drugs = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int drugId = resultSet.getInt("drugId");
                String drugName = resultSet.getString("drugName");
                String drugGenericName = resultSet.getString("drugGenericName");
                String drugDosage = resultSet.getString("drugDosage");
                String drugStrength = resultSet.getString("drugStrength");

                int supplierId = resultSet.getInt("supplierId");
                String supplierName = resultSet.getString("supplierName");
                String supplierAddress = resultSet.getString("supplierAddress");
                String supplierPhoneNumber = resultSet.getString("supplierPhoneNumber");
                String supplierEmail = resultSet.getString("supplierEmail");

                int stockQuantity = resultSet.getInt("stockQuantity");
                int stockPrice = resultSet.getInt("stockPrice");

                drugs.add(new Drug(
                        drugId,
                        drugName,
                        drugGenericName,
                        drugDosage,
                        drugStrength,
                        new Supplier(
                                supplierId,
                                supplierName,
                                supplierAddress,
                                supplierPhoneNumber,
                                supplierEmail
                        ),
                        new Stock(
                                stockPrice,
                                stockQuantity
                        ))
                );
            }
        }

        System.out.println(drugs.size());
        return drugs;
    }

    // update
    public void update(int id, String name, String genericName, String dosage, String strength, int supplierID) throws SQLException {
        final String sql = "UPDATE `drug` SET `name`=?, `genericName`=?, `dosage`=?, `strength`=?, `supplierId`=? WHERE `id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, genericName);
            statement.setString(3, dosage);
            statement.setString(4, strength);
            statement.setInt(5, supplierID);
            statement.setInt(6, id);

            statement.executeUpdate();
        }
    }

    // delete
    public void delete(int id) throws SQLException {
        final String sql = "DELETE FROM `drug` WHERE `id`=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }



}
