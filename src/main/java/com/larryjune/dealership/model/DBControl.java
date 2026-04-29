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
    /**
     * Fetches all entries from the Accidents Database
     * @return An ArrayList of Accident Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Accident> fetchAccidents() throws Exception {
        ArrayList<Accident> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM AccidentData";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("vehicleID");
                ArrayList<Vehicle> v = fetchVehicleDataAt("vehicleID", ""+id, "=");
                results.add(new Accident(
                        rs.getInt("accidentID"),
                        v.get(0),
                        rs.getDate("dateOFAccident"),
                        rs.getString("severity"),
                        rs.getBoolean("airbagDeployment"),
                        rs.getString("descOfAccident")));
            }
        }

        return results;
    }

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Accident Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Accident> fetchAccidentsAt(String column, String val, String sign) throws Exception {
        ArrayList<Accident> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM AccidentData WHERE " + column + " "+ sign +" ?";
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
                int id = rs.getInt("vehicleID");
                ArrayList<Vehicle> v = fetchVehicleDataAt("vehicleID", ""+id, "=");
                results.add(new Accident(
                        rs.getInt("accidentID"),
                        v.get(0),
                        rs.getDate("dateOFAccident"),
                        rs.getString("severity"),
                        rs.getBoolean("airbagDeployment"),
                        rs.getString("descOfAccident")));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Accounts Database
     * @return An ArrayList of Accounts Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Account> fetchAccounts() throws Exception {
        ArrayList<Account> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM Accounts";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Account(
                        rs1.getInt("accountID"),
                        rs1.getString("firstName"),
                        rs1.getString("lastName"),
                        rs1.getString("email"),
                        rs1.getString("phone"),
                        rs1.getString("shippingAddress"),
                        rs1.getString("accountPassword")
                    ));
            }
        }

        return results;
    }

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Account Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Account> fetchAccountsAt(String column, String val) throws Exception {
        ArrayList<Account> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM Accounts WHERE " + column + " = ?";
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
                case "accountPassword":
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
                        rs1.getString("shippingAddress"),
                        rs1.getString("accountPassword")));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Customers Database
     * @return An ArrayList of Customer Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Customer> fetchCustomer() throws Exception {
        ArrayList<Customer> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress " +
            "FROM CustomerAccount cus "+
            "JOIN Accounts acc ON cus.customerAccountID = acc.accountID";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                results.add(new Customer(
                        rs1.getInt("accountID"),
                        rs1.getString("firstName"),
                        rs1.getString("lastName"),
                        rs1.getString("email"),
                        rs1.getString("phone"),
                        rs1.getString("shippingAddress"),
                        rs1.getString("accountPassword")));
            }
        }

        return results;
    }

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Customer Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Customer> fetchCustomerAt(String column, String val, String sign) throws Exception {
        ArrayList<Customer> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress " +
            "FROM CustomerAccount cus "+
            "JOIN Accounts acc ON cus.customerAccountID = acc.accountID "+
            "WHERE "+column+" "+sign+" ?";
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
                case "accountPassword":
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
                        rs1.getString("shippingAddress"),
                        rs1.getString("accountPassword")));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Damage Database
     * @return An ArrayList of Damage Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Damage> fetchDamage() throws Exception {
        ArrayList<Damage> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {
            String sql = "SELECT * FROM damage";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                int vid = rs1.getInt("vehicleID");
                int aid = rs1.getInt("accidentID");
                results.add(new Damage(
                        rs1.getInt("damageID"),
                        fetchVehicleDataAt("vehicleID", ""+vid, "=").get(0),
                        fetchAccidentsAt("accidentID", ""+aid, "=").get(0).getDateOfAccident(),
                        rs1.getString("locationOfDamage"),
                        rs1.getString("severity"),
                        rs1.getInt("repairCost"),
                        fetchAccidentsAt("accidentID", ""+aid, "=").get(0)));
            }
        }

        return results;
    }
    
    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Damage Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Damage> fetchDamageAt(String column, String val, String sign) throws Exception {
        ArrayList<Damage> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {
            String sql = "SELECT * FROM damage WHERE " + column + " "+sign+ " ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "damageID":
                case "vehicleID":
                case "repairCost":
                case "accidentID":
                    stmt.setInt(1, Integer.parseInt(val));
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

            while (rs1.next()) {
                int vid = rs1.getInt("vehicleID");
                int aid = rs1.getInt("accidentID");
                results.add(new Damage(
                        rs1.getInt("damageID"),
                        fetchVehicleDataAt("vehicleID", ""+vid, "=").get(0),
                        fetchAccidentsAt("accidentID", ""+aid, "=").get(0).getDateOfAccident(),
                        rs1.getString("locationOfDamage"),
                        rs1.getString("severity"),
                        rs1.getInt("repairCost"),
                        fetchAccidentsAt("accidentID", ""+aid, "=").get(0)));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Employee Database
     * @return An ArrayList of Employee Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Employee> fetchEmployee() throws Exception {
        ArrayList<Employee> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales " +
            "FROM EmployeeAccount emp "+
            "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID";
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
                        rs1.getInt("totalSales"),
                        rs1.getString("accountPassword")));
            }
        }

        return results;
    }

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Employee Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Employee> fetchEmployeeAt(String column, String val, String sign) throws Exception {
        ArrayList<Employee> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales " +
            "FROM EmployeeAccount emp "+
            "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID " +
            "WHERE "+column+" "+sign+" ?";
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
                case "accountPassword":
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
                        rs1.getInt("totalSales"),
                        rs1.getString("accountPassword")));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Images Database
     * @return An ArrayList of Images Objects from the database
     * @throws Exception Failure to fetch
     */
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

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Image Objects from the database 
     * @throws Exception Failure to fetch
     */
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

    /**
     * Fetches all entries from the Managers Database
     * @return An ArrayList of Managers Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Manager> fetchManagers() throws Exception {
        ArrayList<Manager> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales, man.managerstatus " +
            "FROM ManagerAccount man "+
            "JOIN EmployeeAccount emp ON man.managerAccountID = emp.employeeAccountID "+
            "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID;";
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
                        rs1.getString("managerstatus"),
                        rs1.getString("accountPassword")));
            }
        }

        return results;
    }

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Manager Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Manager> fetchManagersAt(String column, String val, String sign) throws Exception {
        ArrayList<Manager> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT acc.accountID, acc.firstName, "+
            "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
            "acc.shippingAddress, emp.totalSales, man.managerstatus " +
            "FROM ManagerAccount man "+
            "JOIN EmployeeAccount emp ON man.managerAccountID = emp.employeeAccountID "+
            "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID " +
            "WHERE "+column+" "+sign+" ?";
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
                case "accountPassword":
                case "managerstatus":
                    stmt.setString(1, val);
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
                        rs1.getString("managerstatus"),
                        rs1.getString("accountPassword")));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Sales Database
     * @return An ArrayList of Sale Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Sale> fetchSales() throws Exception {
        ArrayList<Sale> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM Sale";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                int vid = rs1.getInt("vehicleID");
                int eid = rs1.getInt("employeeAccountID");
                int cid = rs1.getInt("customerAccountID");
                results.add(new Sale(
                        rs1.getInt("saleID"),
                        fetchVehicleDataAt("vehicleID", vid+"", "=").get(0),
                        fetchEmployeeAt("employeeAccountID", eid+"", "=").get(0),
                        fetchCustomerAt("customerAccountID", cid+"", "=").get(0),
                        rs1.getDate("dateOFSale"),
                        rs1.getInt("amountPaid")));
            }
        }

        return results;
    }

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Sale Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Sale> fetchSalesAt(String column, String val, String sign) throws Exception {
        ArrayList<Sale> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM Sale WHERE " + column + " " +sign +" ?";
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
                int vid = rs1.getInt("vehicleID");
                int eid = rs1.getInt("employeeAccountID");
                int cid = rs1.getInt("customerAccountID");
                results.add(new Sale(
                        rs1.getInt("saleID"),
                        fetchVehicleDataAt("vehicleID", vid+"", "=").get(0),
                        fetchEmployeeAt("employeeAccountID", eid+"", "=").get(0),
                        fetchCustomerAt("customerAccountID", cid+"", "=").get(0),
                        rs1.getDate("dateOFSale"),
                        rs1.getInt("amountPaid")));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Service Database
     * @return An ArrayList of Service Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Service> fetchService() throws Exception {
        ArrayList<Service> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM Service";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs1 = stmt.executeQuery();

            while (rs1.next()) {
                int vid = rs1.getInt("vehicleID");
                results.add(new Service(
                        rs1.getInt("serviceID"),
                        fetchVehicleDataAt("vehicleID", ""+vid, "=").get(0),
                        rs1.getDate("dateOfService"),
                        rs1.getString("descriptionOFService"),
                        rs1.getInt("cost"),
                        rs1.getInt("mileage")));
            }
        }

        return results;
    }

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Service Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Service> fetchServiceAt(String column, String val, String sign) throws Exception {
        ArrayList<Service> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM Service WHERE " + column + " "+sign+" ?";
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
                int vid = rs1.getInt("vehicleID");
                results.add(new Service(
                        rs1.getInt("serviceID"),
                        fetchVehicleDataAt("vehicleID", ""+vid, "=").get(0),
                        rs1.getDate("dateOfService"),
                        rs1.getString("descriptionOFService"),
                        rs1.getInt("cost"),
                        rs1.getInt("mileage")));
            }
        }

        return results;
    }

    /**
     * Fetches all entries from the Vehicle Database
     * @return An ArrayList of Vehicle Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Vehicle> fetchVehicleData() throws Exception {
        ArrayList<Vehicle> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM vehicleData";
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

    /**
     * Fetches entries that match a specfic criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Vehicle Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Vehicle> fetchVehicleDataAt(String column, String val, String sign) throws Exception {
        ArrayList<Vehicle> results = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = "SELECT * FROM vehicleData WHERE " + column + " " + sign + " ?";
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
    /**
     * Inserts a new entry to the Accident Database
     * @param n the accident entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertAccident(Accident n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO AccidentData (vehicleID, dateOfAccident, severity, descOfAccident, airbagDeployment) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getVehicle().getVehicleID());
            stmt.setDate(2, n.getDateOfAccident());
            stmt.setString(3, n.getSeverity());
            stmt.setString(4, n.getDescription());
            stmt.setBoolean(5, n.isAirbagDeployment());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    /**
     * Inserts a new entry to the Account Database
     * @param n the account entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertAccount(Account n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO Accounts (accountID, firstName, lastName, phone, accountPassword, email, shippingAddress) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccountID());
            stmt.setString(2, n.getFirstName());
            stmt.setString(3, n.getLastName());
            stmt.setString(4, n.getPhoneNum());
            stmt.setString(5, n.getPassword()); //Please make a password attribute for account
            stmt.setString(6, n.getEmail());
            stmt.setString(7, n.getShippingAddress());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    /**
     * Inserts a new entry to the Customer Database
     * @param n the customer entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertCustomer(Customer n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            InsertAccount(new Account(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), n.getPhoneNum(), n.getShippingAddress(), n.getPassword()));
            String sql = "INSERT INTO CustomerAccount (customerAccountID) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccountID());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * Inserts a new entry to the Damage Database
     * @param n the damage entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertDamage(Damage n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO damage (vehicleID, locationOfDamage, severity, repairCost, accidentID, airbagDeployment) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getVehicle().getVehicleID());
            stmt.setString(2, n.getDamageLocation());
            stmt.setString(3, n.getSeverity());
            stmt.setDouble(4, n.getRepairCost());
            stmt.setInt(5, n.getAccident().getAccidentID());
            stmt.setBoolean(6, n.getAccident().isAirbagDeployment());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    /**
     * Inserts a new entry to the Employee Database
     * @param n the employee entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertEmployee(Employee n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            InsertAccount(new Account(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), n.getPhoneNum(), n.getShippingAddress(), n.getPassword()));
            String sql = "INSERT INTO EmployeeAccount (employeeAccountID, totalSales) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getAccountID());
            stmt.setDouble(2, n.getTotalSalesPerMonth());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    /**
     * Inserts a new entry to the Image Database
     * @param n the Image entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertImage(Image n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO images (imageID, vehicleID, imageURL) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getImageID());
            stmt.setInt(2, n.getVehicleID());
            stmt.setString(3, n.getImagePath());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    /**
     * Inserts a new entry to the Manager Database
     * @param n the manager entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertManager(Manager n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            InsertEmployee(new Employee(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), n.getPhoneNum(), n.getShippingAddress(), n.getTotalSalesPerMonth(), n.getPassword()));
            String sql = "INSERT INTO ManagerAccount (managerAccountID, managerstatus) VALUES (?, ?)";
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

    /**
     * Inserts a new entry to the Sale Database
     * @param n the Sale entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertSale(Sale n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO Sale (vehicleID, employeeAccountID, customerAccountID, dateOFSale, amountPaid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getVehicle().getVehicleID());
            stmt.setInt(2, n.getEmployeeAccount().getAccountID());
            stmt.setInt(3, n.getCustomerAccount().getAccountID());
            stmt.setDate(4, n.getSaleDate());
            stmt.setDouble(5, n.getSaleAmount());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    } 

    /**
     * Inserts a new entry to the Service Database
     * @param n the Service entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertService(Service n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO Service (vehicleID, dateOfService, descriptionOFService, cost, mileage) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, n.getVehicle().getVehicleID());
            stmt.setDate(2, n.getDateOfService());
            stmt.setString(3, n.getDescription());
            stmt.setDouble(4, n.getCost());
            stmt.setInt(5, n.getMileageAtService());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    /**
     * Inserts a new entry to the Vehicle Database
     * @param n the vehicle entry
     * @return if the entry is sucessfully added, then "true", otherwise "false"
     * @throws Exception Failure to add, prints "false"
     */
    public static boolean InsertVehicle(Vehicle n) throws Exception {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO vehicleData (vinNumber, price, maker, model, color, modelYear, bodyStyle, isUsed, mileage, carStatus, prevOwnerCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, n.getVinNumber());
            stmt.setDouble(2, n.getPrice());
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

    //Update Methods
    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateAccidents(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE AccidentData SET " + column+ " = ? WHERE accidentID = ?";
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
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateAccount(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE Accounts SET " + column+ " = ? WHERE accountID = ?";
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
                case "accountPassword":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateDamage(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE damage SET " + column+ " = ? WHERE damageID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "damageID":
                case "vehicleID":
                case "repairCost":
                case "accidentID":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "locationOfDamage":
                case "severity":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateEmployee(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE EmployeeAccount SET " + column+ " = ? WHERE employeeAccountID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "employeeAccountID":
                case "totalSales":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateImages(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE images SET " + column+ " = ? WHERE imageID = ?";
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
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateManager(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE ManagerAccount SET " + column+ " = ? WHERE managerAccountID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            switch (column) {
                case "managerAccountID":
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
                case "managerstatus":
                    stmt.setString(1, val);
                    break;
                default:
                    stmt.setInt(1, Integer.parseInt(val));
                    break;
            }
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateSale(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE Sale SET " + column+ " = ? WHERE saleID = ?";
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
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateService(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE Service SET " + column+ " = ? WHERE serviceID = ?";
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
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }

    /**
     * Updates entrys in the table
     * @param id the id
     * @param column column
     * @param val value
     * @return True if it updates, false otherwise
     * @throws Exception returns false
     */
    public static boolean updateVehicleData(int id, String column, String val) throws Exception {
        try (Connection conn = DBConnection.connect()) {

            String sql = "UPDATE vehicleData SET " + column+ " = ? WHERE vehicleID = ?";
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
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            return false;        
        }

        return true;
    }
}