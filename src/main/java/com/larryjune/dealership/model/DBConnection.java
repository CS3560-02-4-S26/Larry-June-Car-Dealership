package com.larryjune.dealership.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class DBConnection {
    /**
     * Connects to a local mySQL database
     * @return a Connection to the database
     * @throws Exception Fail to connect
     */
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://gperson.chickenkiller.com:3306/larryjunedatabase";
        String user = "larry";
        String password = "ILoveCars2000";

        return DriverManager.getConnection(url, user, password);
    }
}
