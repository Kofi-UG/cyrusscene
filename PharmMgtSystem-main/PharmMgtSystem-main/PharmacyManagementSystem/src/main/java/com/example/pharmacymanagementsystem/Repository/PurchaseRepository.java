package com.example.pharmacymanagementsystem.Repository;

import com.example.pharmacymanagementsystem.Database.DBConnection;
import com.example.pharmacymanagementsystem.Mapper.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseRepository {
    private static Connection connection;

    public PurchaseRepository() throws SQLException {
        connection = DBConnection.connection();
    }

    // insert
    public void insert(int drugId, int quantity, int price, int buyerId) throws SQLException {
        final String sql = "INSERT INTO `purchase`(`drugId`, `quantity`, `price`, `buyerId`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, drugId);
            statement.setInt(2, quantity);
            statement.setInt(3, price);
            statement.setInt(4, buyerId);

            statement.executeUpdate();

        }
    }

    // read by id
    // SELECT `id`, `drugId`, `quantity`, `price`, `buyerId`, `date` FROM `purchase` WHERE 1
    public Purchase read(int id) throws SQLException {
        final String sql = "SELECT " +
                "`purchase`.`id` AS purchaseId, " +
                "`drug`.`id` AS drugId, " +
                "`drug`.`name` AS drugName, " +
                "`drug`.`genericName` AS drugGenericName, " +
                "`drug`.`dosage` AS drugDosage, " +
                "`drug`.`strength` AS drugStrength, " +
                "`purchase`.`quantity` AS purchaseQuantity, " +
                "`purchase`.`price` AS purchasePrice, " +
                "`buyer`.`id` AS buyerId, " +
                "`buyer`.`name` AS buyerName, " +
                "`buyer`.`phoneNumber` AS buyerPhoneNumber, " +
                "`buyer`.`address` AS buyerAddress, " +
                "`purchase`.`date` AS purchaseDate " +
                "FROM `purchase` " +
                "INNER JOIN `drug` ON `drug`.`id` = `purchase`.`drugId` " +
                "INNER JOIN `buyer` ON `buyer`.`id` = `purchase`.`buyerId` " +
                "WHERE `purchase`.`id` = ?";


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int purchaseId = resultSet.getInt("purchaseId");

                int drugId = resultSet.getInt("drugId");
                String drugName = resultSet.getString("drugName");
                String drugGenericName = resultSet.getString("drugGenericName");
                String drugDosage = resultSet.getString("drugDosage");
                String drugStrength = resultSet.getString("drugStrength");

                int purchaseQuantity = resultSet.getInt("purchaseQuantity");
                int purchasePrice = resultSet.getInt("purchasePrice");

                int buyerId = resultSet.getInt("buyerId");
                String buyerName = resultSet.getString("buyerName");
                String buyerPhoneNumber = resultSet.getString("buyerPhoneNumber");
                String buyerAddress = resultSet.getString("buyerAddress");

                String purchaseDate = resultSet.getString("purchaseDate");

                Stock stock = new Stock(-1, -1);
                Supplier supplier = new Supplier(-1, "", "", "", "");
                Drug drug = new Drug(drugId, drugName, drugGenericName, drugDosage, drugStrength, supplier, stock);
                Buyer buyer = new Buyer(buyerId, buyerName, buyerPhoneNumber, buyerAddress);

                return new Purchase(purchaseId, drug, purchaseQuantity, purchasePrice, buyer, purchaseDate);
            }
        }

        throw new SQLException("It seems there is no record with such id");
    }

    // read all
    // SELECT `id`, `drugId`, `quantity`, `price`, `buyerId`, `date` FROM `purchase` WHERE 1
    public List<Purchase> read() throws SQLException {
        final String sql = "SELECT " +
                "`purchase`.`id` AS purchaseId, " +
                "`drug`.`id` AS drugId, " +
                "`drug`.`name` AS drugName, " +
                "`drug`.`genericName` AS drugGenericName, " +
                "`drug`.`dosage` AS drugDosage, " +
                "`drug`.`strength` AS drugStrength, " +
                "`purchase`.`quantity` AS purchaseQuantity, " +
                "`purchase`.`price` AS purchasePrice, " +
                "`buyer`.`id` AS buyerId, " +
                "`buyer`.`name` AS buyerName, " +
                "`buyer`.`phoneNumber` AS buyerPhoneNumber, " +
                "`buyer`.`address` AS buyerAddress, " +
                "`purchase`.`date` AS purchaseDate " +
                "FROM `purchase` " +
                "INNER JOIN `drug` ON `drug`.`id` = `purchase`.`drugId` " +
                "INNER JOIN `buyer` ON `buyer`.`id` = `purchase`.`buyerId`";


        List<Purchase> purchases = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int purchaseId = resultSet.getInt("purchaseId");

                int drugId = resultSet.getInt("drugId");
                String drugName = resultSet.getString("drugName");
                String drugGenericName = resultSet.getString("drugGenericName");
                String drugDosage = resultSet.getString("drugDosage");
                String drugStrength = resultSet.getString("drugStrength");

                int purchaseQuantity = resultSet.getInt("purchaseQuantity");
                int purchasePrice = resultSet.getInt("purchasePrice");

                int buyerId = resultSet.getInt("buyerId");
                String buyerName = resultSet.getString("buyerName");
                String buyerPhoneNumber = resultSet.getString("buyerPhoneNumber");
                String buyerAddress = resultSet.getString("buyerAddress");

                String purchaseDate = resultSet.getString("purchaseDate");

                Stock stock = new Stock(-1, -1);
                Supplier supplier = new Supplier(-1, "", "", "", "");
                Drug drug = new Drug(drugId, drugName, drugGenericName, drugDosage, drugStrength, supplier, stock);
                Buyer buyer = new Buyer(buyerId, buyerName, buyerPhoneNumber, buyerAddress);

                purchases.add(new Purchase(purchaseId, drug, purchaseQuantity, purchasePrice, buyer, purchaseDate));
            }
        }

        return purchases;
    }

    // update

    // delete
}
