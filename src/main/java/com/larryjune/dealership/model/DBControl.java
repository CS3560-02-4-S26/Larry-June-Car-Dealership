package com.larryjune.dealership.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DBControl {
    // Fetching
    public static ArrayList<Accident> fetchAccidents() throws Exception {
        ArrayList<Accident> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM accidentdata";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new Accident(
                    rs.getInt("accidentID"), 
                    rs.getInt("vehicleID"), 
                    rs.getDate("dateOFAccident"), 
                    rs.getString("severity"), 
                    rs.getBoolean("airbagDeployment"), 
                    rs.getString("descOfAccident")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Account> fetchAccounts() throws Exception {
        ArrayList<Account> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM accounts";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Account(
                    rs1.getInt("accountID"), 
                    rs1.getString("firstName"), 
                    rs1.getString("lastName"),
                    rs1.getString("email"), 
                    rs1.getString("phone"), 
                    rs1.getString("shippingAddress")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Customer> fetchCustomer() throws Exception {
        ArrayList<Customer> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM accounts";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Customer(
                    rs1.getInt("accountID"), 
                    rs1.getString("firstName"), 
                    rs1.getString("lastName"),
                    rs1.getString("email"), 
                    rs1.getString("phone"), 
                    rs1.getString("shippingAddress")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Damage> fetchDamage() throws Exception {
        ArrayList<Damage> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {
            String sql1 = "SELECT * FROM accidentdata";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            ResultSet rs2 = stmt1.executeQuery();

            String sql = "SELECT * FROM accounts";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Damage(
                    rs1.getInt("damageID"), 
                    rs1.getInt("vehicleID"),
                    rs2.getDate("dateOFAccident"), 
                    rs1.getString("locationOfDamage"), 
                    rs1.getString("severity"), 
                    rs1.getInt("repairCost"), 
                    rs1.getInt("accidentID")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Employee> fetchEmployee() throws Exception {
        ArrayList<Employee> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM employeeaccount";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Employee(
                    rs1.getInt("accountID"), 
                    rs1.getString("firstName"), 
                    rs1.getString("lastName"),
                    rs1.getString("email"), 
                    rs1.getString("phone"), 
                    rs1.getString("shippingAddress"), 
                    rs1.getInt("totalSales")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Image> fetchImages() throws Exception {
        ArrayList<Image> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM images";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Image(
                    rs1.getInt("imageID"), 
                    rs1.getInt("vehicleID"), 
                    rs1.getString("imageURL")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Manager> fetchManagers() throws Exception {
        ArrayList<Manager> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM manageraccount";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Manager(
                    rs1.getInt("accountID"), 
                    rs1.getString("firstName"), 
                    rs1.getString("lastName"),
                    rs1.getString("email"), 
                    rs1.getString("phone"), 
                    rs1.getString("shippingAddress"), 
                    rs1.getInt("totalSales"),
                    rs1.getString("managerstatus")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Sale> fetchSales() throws Exception {
        ArrayList<Sale> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM sale";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Sale(
                    rs1.getInt("saleID"),
                    rs1.getInt("vehicleID"), 
                    rs1.getInt("employeeAccountID"), 
                    rs1.getInt("customerAccountID"), 
                    rs1.getDate("dateOFSale"), 
                    rs1.getInt("amountPaid")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Service> fetchService() throws Exception {
        ArrayList<Service> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM service";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Service(
                    rs1.getInt("serviceID"), 
                    rs1.getInt("vehicleID"), 
                    rs1.getDate("dateOfService"), 
                    rs1.getString("descriptionOFService"), 
                    rs1.getInt("cost"), 
                    rs1.getInt("mileage")
                ));
            }
        }

        return results;
    }

    public static ArrayList<Vehicle> fetchVehicleData() throws Exception {
        ArrayList<Vehicle> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM vehicledata";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Vehicle(
                    rs1.getInt("vehicleID"), 
                    rs1.getString("vinNumber"), 
                    rs1.getInt("price"), 
                    rs1.getString("maker"), 
                    rs1.getString("model"), 
                    rs1.getString("color"), 
                    rs1.getInt("modelYear"), 
                    rs1.getString("bodyStyle"), 
                    rs1.getBoolean("isUsed"), 
                    rs1.getInt("mileage"), 
                    rs1.getString("carStatus"), 
                    rs1.getInt("prevOwnerCount")
                ));
            }
        }

        return results;
    }

    // Inserting
    public static boolean InsertAccident(Accident n){return true;} //Todo

    public static boolean InsertAccount(Account n){return true;} //Todo

    public static boolean InsertCustomer(Customer n){return true;} //Todo

    public static boolean InsertDamage(Damage n){return true;} //Todo

    public static boolean InsertEmployee(Employee n){return true;} //Todo

    public static boolean InsertImage(Image n){return true;} //Todo

    public static boolean InsertManager(Manager n){return true;} //Todo

    public static boolean InsertSale(Sale n){return true;} //Todo

    public static boolean InsertService(Service n){return true;} //Todo

    public static boolean InsertVehicle(Vehicle n) throws Exception{
        try (Connection conn = DBConnection.connect()) {

            String sql = "INSERT INTO vehicledata (vinNumber, price, maker, model, color, modelYear, bodyStyle, isUsed, mileage, carStatus, prevOwnerCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, n.getVinNumber());
            stmt.setInt(2, (int) n.getPrice());
            stmt.setString(3, n.getMake());
            stmt.setString(4, n.getModel());
            stmt.setString(5, n.getColor());
            stmt.setInt(6, n.getYear());
            stmt.setString(7, n.getBodyStyle());
            stmt.setBoolean(8, n.isUsed());
            stmt.setInt(9, n.getMileage());
            stmt.setBoolean(10, true);
            stmt.setInt(11, n.getPreviousOwnerCount());
            stmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e){
            return false;
        }
        
        return true;
    }
}