package com.larryjune.dealership.model;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    /**
     * Connects to a local mySQL database
     * @return a Connection to the database
     * @throws Exception Fail to connect
     */
    public static Connection connect() throws Exception {
        String url = "jdbc:mysql://gperson.chickenkiller.com:3306/larryjunedatabase";
        String user = "larry";
        String password = "ILoveCars2000"; // Bad for the real world, but for demo purposes this works.

        return DriverManager.getConnection(url, user, password);
    }
}
