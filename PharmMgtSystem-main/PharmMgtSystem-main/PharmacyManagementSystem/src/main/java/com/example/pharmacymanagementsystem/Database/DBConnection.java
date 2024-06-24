package com.example.pharmacymanagementsystem.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    public static Connection connection() throws SQLException {
        String connectionString = "jdbc:mysql://localhost/dcit_308_pharmarcy_management_app?user=root&password=password";
        Connection connection = DriverManager.getConnection(connectionString);
        System.out.println("Db connected successfully");
        return connection;
    }
}