package com.larryjune.dealership.model;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/larryjunedatabase";
        String user = "root";
        String password = "Goldania1978MG!@";

        return DriverManager.getConnection(url, user, password);
    }
}