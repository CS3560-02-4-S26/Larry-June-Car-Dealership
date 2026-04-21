package com.larryjune.dealership.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBControl {
    // Fetching
    // NOTE: Dates are always in the format of yyyy-mm-dd
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
                        rs.getString("descOfAccident")));
            }
        }

        return results;
    }

    public static ArrayList<Accident> fetchAccidentsAt(String column, String val) throws Exception {
        ArrayList<Accident> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM accidentdata WHERE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "accidentID":
                case "vehicleID":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "dateOFAccident":
                    stmt.setDate(1, Date.valueOf(val));
                    break;
                case "severity":
                case "descOfAccident":
                    stmt.setString(1, val);
                    break;
                case "airbagDeployment":
                    if (val.equals("true")) {
                        stmt.setBoolean(1, true);
                    } else {
                        stmt.setBoolean(1, false);
                    }
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new Accident(
                        rs.getInt("accidentID"),
                        rs.getInt("vehicleID"),
                        rs.getDate("dateOFAccident"),
                        rs.getString("severity"),
                        rs.getBoolean("airbagDeployment"),
                        rs.getString("descOfAccident")));
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
                        rs1.getString("shippingAddress")));
            }
        }

        return results;
    }

    public static ArrayList<Account> fetchAccountsAt(String column, String val) throws Exception {
        ArrayList<Account> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM accounts WHERE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "accountID":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "firstName":
                case "lastName":
                case "email":
                case "phone":
                case "shippingAddress":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Account(
                        rs1.getInt("accountID"),
                        rs1.getString("firstName"),
                        rs1.getString("lastName"),
                        rs1.getString("email"),
                        rs1.getString("phone"),
                        rs1.getString("shippingAddress")));
            }
        }

        return results;
    }

    public static ArrayList<Customer> fetchCustomer() throws Exception {
        ArrayList<Customer> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress " +
            "FROM customeraccount cus "+
            "JOIN accounts acc ON cus.customerAccountID = acc.accountID";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Customer(
                        rs1.getInt("accountID"),
                        rs1.getString("firstName"),
                        rs1.getString("lastName"),
                        rs1.getString("email"),
                        rs1.getString("phone"),
                        rs1.getString("shippingAddress")));
            }
        }

        return results;
    }

    public static ArrayList<Customer> fetchCustomerAt(String column, String val) throws Exception {
        ArrayList<Customer> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress " +
            "FROM customeraccount cus "+
            "JOIN accounts acc ON cus.customerAccountID = acc.accountID "+
            "WHILE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "accountID":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "firstName":
                case "lastName":
                case "email":
                case "phone":
                case "shippingAddress":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Customer(
                        rs1.getInt("accountID"),
                        rs1.getString("firstName"),
                        rs1.getString("lastName"),
                        rs1.getString("email"),
                        rs1.getString("phone"),
                        rs1.getString("shippingAddress")));
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

            String sql = "SELECT * FROM damage";
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
                        rs1.getInt("accidentID")));
            }
        }

        return results;
    }
    
    public static ArrayList<Damage> fetchDamageAt(String column, String val) throws Exception {
        ArrayList<Damage> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {
            String sql = "SELECT * FROM damage WHERE " + column + " = ?";
            String sql1 = "SELECT * FROM accidentdata WHERE accidentdata = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "damageID":
                case "vehicleID":
                case "repairCost":
                case "accidentID":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                //Note: Date of Accident does not work well right now
                //Fixing databases later TODO.
                case "dateOFAccident":
                    stmt1.setDate(1, Date.valueOf(val));
                    break;
                case "locationOfDamage":
                case "severity":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            ResultSet rs1 = stmt.executeQuery();
            ResultSet rs2 = stmt1.executeQuery();


            while (rs1.next()) {
                results.add(new Damage(
                        rs1.getInt("damageID"),
                        rs1.getInt("vehicleID"),
                        rs2.getDate("dateOFAccident"),
                        rs1.getString("locationOfDamage"),
                        rs1.getString("severity"),
                        rs1.getInt("repairCost"),
                        rs1.getInt("accidentID")));
            }
        }

        return results;
    }

    public static ArrayList<Employee> fetchEmployee() throws Exception {
        ArrayList<Employee> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales " +
            "FROM employeeaccount emp "+
            "JOIN accounts acc ON emp.employeeAccountID = acc.accountID";
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
                        rs1.getInt("totalSales")));
            }
        }

        return results;
    }

    public static ArrayList<Employee> fetchEmployeeAt(String column, String val) throws Exception {
        ArrayList<Employee> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales " +
            "FROM employeeaccount emp "+
            "JOIN accounts acc ON emp.employeeAccountID = acc.accountID " +
            "WHERE "+column+" = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "accountID":
                case "totalSales":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "firstName":
                case "lastName":
                case "email":
                case "phone":
                case "shippingAddress":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Employee(
                        rs1.getInt("accountID"),
                        rs1.getString("firstName"),
                        rs1.getString("lastName"),
                        rs1.getString("email"),
                        rs1.getString("phone"),
                        rs1.getString("shippingAddress"),
                        rs1.getInt("totalSales")));
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
                        rs1.getString("imageURL")));
            }
        }

        return results;
    }

    public static ArrayList<Image> fetchImagesAt(String column, String val) throws Exception {
        ArrayList<Image> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM images WHERE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "imageID":
                case "vehicleID":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "imageURL":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Image(
                        rs1.getInt("imageID"),
                        rs1.getInt("vehicleID"),
                        rs1.getString("imageURL")));
            }
        }

        return results;
    }

    public static ArrayList<Manager> fetchManagers() throws Exception {
        ArrayList<Manager> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales, man.managerstatus " +
            "FROM manageraccount man "+
            "JOIN employeeaccount emp ON man.managerAccountID = emp.employeeAccountID "+
            "JOIN accounts acc ON emp.employeeAccountID = acc.accountID;";
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
                        rs1.getString("managerstatus")));
            }
        }

        return results;
    }

    public static ArrayList<Manager> fetchManagersAt(String column, String val) throws Exception {
        ArrayList<Manager> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales, man.managerstatus " +
            "FROM manageraccount man "+
            "JOIN employeeaccount emp ON man.managerAccountID = emp.employeeAccountID "+
            "JOIN accounts acc ON emp.employeeAccountID = acc.accountID " +
            "WHERE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "accountID":
                case "totalSales":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "firstName":
                case "lastName":
                case "email":
                case "phone":
                case "shippingAddress":
                    stmt.setString(1, val);
                    break;
                case "managerstatus":
                    if (val.equals("true")) {
                        stmt.setBoolean(1, true);
                    } else {
                        stmt.setBoolean(1, false);
                    }
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
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
                        rs1.getString("managerstatus")));
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
                        rs1.getInt("amountPaid")));
            }
        }

        return results;
    }

    public static ArrayList<Sale> fetchSalesAt(String column, String val) throws Exception {
        ArrayList<Sale> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM sale WHERE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "saleID":
                case "vehicleID":
                case "employeeAccountID":
                case "customerAccountID":
                case "amountPaid":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "dateOFSale":
                    stmt.setDate(1, Date.valueOf(val));
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Sale(
                        rs1.getInt("saleID"),
                        rs1.getInt("vehicleID"),
                        rs1.getInt("employeeAccountID"),
                        rs1.getInt("customerAccountID"),
                        rs1.getDate("dateOFSale"),
                        rs1.getInt("amountPaid")));
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
                        rs1.getInt("mileage")));
            }
        }

        return results;
    }

    public static ArrayList<Service> fetchServiceAt(String column, String val) throws Exception {
        ArrayList<Service> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM service WHERE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "serviceID":
                case "vehicleID":
                case "cost":
                case "mileage":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "dateOfService":
                    stmt.setDate(1, Date.valueOf(val));
                    break;
                case "severity":
                case "descriptionOFService":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Service(
                        rs1.getInt("serviceID"),
                        rs1.getInt("vehicleID"),
                        rs1.getDate("dateOfService"),
                        rs1.getString("descriptionOFService"),
                        rs1.getInt("cost"),
                        rs1.getInt("mileage")));
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
                        rs1.getInt("prevOwnerCount")));
            }
        }

        return results;
    }

    public static ArrayList<Vehicle> fetchVehicleDataAt(String column, String val) throws Exception {
        ArrayList<Vehicle> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM vehicledata WHERE " + column + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "price":
                case "vehicleID":
                case "modelYear":
                case "mileage":
                case "prevOwnerCount":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "dateOFAccident":
                    stmt.setDate(1, Date.valueOf(val));
                    break;
                case "vinNumber":
                case "maker":
                case "model":
                case "color":
                case "bodyStyle":
                //DO NOT USE CAR STATUS RIGHT NOW IM GONNA FIX THE DATABASES SOON
                case "carStatus":
                    stmt.setString(1, val);
                    break;
                case "isUsed":
                    if (val.equals("true")) {
                        stmt.setBoolean(1, true);
                    } else {
                        stmt.setBoolean(1, false);
                    }
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
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
                        rs1.getInt("prevOwnerCount")));
            }
        }

        return results;
    }

    // Inserting
    public static boolean InsertAccident(Accident n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO accidentdata (accidentID, vehicleID, dateOfAccident, severity, descOfAccident, airbagDeployment) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccidentID());
            stmt.setInt(2, n.getVehicleID());
            stmt.setDate(3, n.getDateOfAccident());
            stmt.setString(4, n.getSeverity());
            stmt.setString(5, n.getDescription()); //Please make a password attribute for account
            stmt.setBoolean(6, n.isAirbagDeployment());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    public static boolean InsertAccount(Account n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO accounts (accountID, firstName, lastName, phone, accountPassword, email, shippingAddress) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccountID());
            stmt.setString(2, n.getFirstName());
            stmt.setString(3, n.getLastName());
            stmt.setString(4, n.getPhoneNum());
            stmt.setString(5, "ABC123"); //Please make a password attribute for account
            stmt.setString(6, n.getEmail());
            stmt.setString(7, n.getShippingAddress());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public static boolean InsertCustomer(Customer n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            InsertAccount(new Account(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), n.getPhoneNum(), n.getShippingAddress()));
            String sql = "INSERT INTO customeraccount (customerAccountID) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccountID());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean InsertDamage(Damage n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO damage (damageID, vehicleID, locationOfDamage, severity, repairCost, accidentID, airbageDeployment) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getDamageID());
            stmt.setInt(2, n.getVehicleID());
            stmt.setString(3, n.getDamageLocation());
            stmt.setString(4, n.getSeverity());
            stmt.setInt(5, (int) n.getRepairCost());
            stmt.setInt(6, n.getAccidentID());
            stmt.setBoolean(7, true);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    public static boolean InsertEmployee(Employee n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            InsertAccount(new Account(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), n.getPhoneNum(), n.getShippingAddress()));
            String sql = "INSERT INTO employeeaccount (employeeAccountID, totalSales) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccountID());
            stmt.setInt(2, (int) n.getTotalSalesPerMonth());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    public static boolean InsertImage(Image n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO images (imageID, vehicleID, imageURL) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getImageID());
            stmt.setInt(2, (int) n.getVehicleID());
            stmt.setString(3, n.getImagePath());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public static boolean InsertManager(Manager n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            InsertEmployee(new Employee(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), n.getPhoneNum(), n.getShippingAddress(), n.getTotalSalesPerMonth()));
            String sql = "INSERT INTO manageraccount (managerAccountID, managerstatus) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccountID());
            stmt.setString(2, n.getStatus());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    public static boolean InsertSale(Sale n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO sale (saleID, vehicleID, employeeAccountID, customerAccountID, dateOFSale, amountPaid) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getSaleID());
            stmt.setInt(2, n.getVehicleID());
            stmt.setInt(3, n.getEmployeeAccountID());
            stmt.setInt(4, n.getCustomerAccountID());
            stmt.setDate(5, n.getSaleDate());
            stmt.setInt(6, (int) n.getSaleAmount());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    public static boolean InsertService(Service n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO service (serviceID, vehicleID, dateOfService, descriptionOFService, cost, mileage) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getServiceID());
            stmt.setInt(2, n.getVehicleID());
            stmt.setDate(3, n.getDateOfService());
            stmt.setString(4, n.getDescription());
            stmt.setInt(5, (int) n.getCost());
            stmt.setInt(6, n.getMileageAtService());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public static boolean InsertVehicle(Vehicle n) throws Exception {
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
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}