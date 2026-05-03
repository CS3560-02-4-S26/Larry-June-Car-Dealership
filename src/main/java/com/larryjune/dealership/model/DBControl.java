package com.larryjune.dealership.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBControl {
    private static Connection mDBConnection;

    private static void initDatabaseConnection() throws SQLException {
        mDBConnection = DBConnection.connect();
    }

    static {
        try {
            initDatabaseConnection();
        } catch (SQLException e) {
            System.err.println("DBControl: Failed to initialize database connection");
            e.printStackTrace();
            mDBConnection = null;
        }
    }

    // Fetching
    // NOTE: Dates are always in the format of yyyy-mm-dd
    /**
     * Fetches all entries from the Accidents Database
     * @return An ArrayList of Accident Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Accident> fetchAccidents() throws Exception {
        ArrayList<Accident> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM AccidentData";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("vehicleID");
            ArrayList<Vehicle> v = fetchVehicleDataAt("vehicleID", ""+id, "=");
            results.add(new Accident(
                    rs.getInt("accidentID"),
                    v.getFirst(),
                    rs.getDate("dateOFAccident"),
                    rs.getString("severity"),
                    rs.getBoolean("airbagDeployment"),
                    rs.getString("descOfAccident")));
        }

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Accident Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Accident> fetchAccidentsAt(String column, String val, String sign) throws Exception {
        ArrayList<Accident> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM AccidentData WHERE " + column + " "+ sign +" ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
                stmt.setBoolean(1, val.equals("true"));
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
                    v.getFirst(),
                    rs.getDate("dateOFAccident"),
                    rs.getString("severity"),
                    rs.getBoolean("airbagDeployment"),
                    rs.getString("descOfAccident")));
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
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM Accounts";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

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

        return results;
    }

    /**
     * Fetches entries that match a specific conncriteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @return An ArrayList of Account Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Account> fetchAccountsAt(String column, String val) throws Exception {
        ArrayList<Account> results = new ArrayList<>();

        String sql = "SELECT * FROM Accounts WHERE " + column + " = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

        return results;
    }

    /**
     * Fetches all entries from the Customers Database
     * @return An ArrayList of Customer Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Customer> fetchCustomer() throws Exception {
        ArrayList<Customer> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT acc.accountID, acc.firstName, "+
        "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
        "acc.shippingAddress " +
        "FROM CustomerAccount cus "+
        "JOIN Accounts acc ON cus.customerAccountID = acc.accountID";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

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

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Customer Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Customer> fetchCustomerAt(String column, String val, String sign) throws Exception {
        ArrayList<Customer> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT acc.accountID, acc.firstName, "+
        "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
        "acc.shippingAddress " +
        "FROM CustomerAccount cus "+
        "JOIN Accounts acc ON cus.customerAccountID = acc.accountID "+
        "WHERE "+column+" "+sign+" ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

        return results;
    }

    /**
     * Fetches all entries from the Damage Database
     * @return An ArrayList of Damage Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Damage> fetchDamage() throws Exception {
        ArrayList<Damage> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM damage";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        ResultSet rs1 = stmt.executeQuery();

        while (rs1.next()) {
            int vid = rs1.getInt("vehicleID");
            int aid = rs1.getInt("accidentID");
            results.add(new Damage(
                    rs1.getInt("damageID"),
                    fetchVehicleDataAt("vehicleID", ""+vid, "=").getFirst(),
                    fetchAccidentsAt("accidentID", ""+aid, "=").getFirst().getDateOfAccident(),
                    rs1.getString("locationOfDamage"),
                    rs1.getString("severity"),
                    rs1.getInt("repairCost"),
                    fetchAccidentsAt("accidentID", ""+aid, "=").getFirst()));
        }

        return results;
    }
    
    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Damage Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Damage> fetchDamageAt(String column, String val, String sign) throws Exception {
        ArrayList<Damage> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM damage WHERE " + column + " "+sign+ " ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
                    fetchVehicleDataAt("vehicleID", ""+vid, "=").getFirst(),
                    fetchAccidentsAt("accidentID", ""+aid, "=").getFirst().getDateOfAccident(),
                    rs1.getString("locationOfDamage"),
                    rs1.getString("severity"),
                    rs1.getInt("repairCost"),
                    fetchAccidentsAt("accidentID", ""+aid, "=").getFirst()));
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
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT acc.accountID, acc.firstName, "+
        "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
        "acc.shippingAddress, emp.totalSales " +
        "FROM EmployeeAccount emp "+
        "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

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

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Employee Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Employee> fetchEmployeeAt(String column, String val, String sign) throws Exception {
        ArrayList<Employee> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT acc.accountID, acc.firstName, "+
        "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
        "acc.shippingAddress, emp.totalSales " +
        "FROM EmployeeAccount emp "+
        "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID " +
        "WHERE "+column+" "+sign+" ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

        return results;
    }

    /**
     * Fetches all entries from the Images Database
     * @return An ArrayList of Images Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Image> fetchImages() throws Exception {
        ArrayList<Image> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM images";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

        ResultSet rs1 = stmt.executeQuery();

        while (rs1.next()) {
            results.add(new Image(
                    rs1.getInt("imageID"),
                    rs1.getInt("vehicleID"),
                    rs1.getString("imageURL")));
        }

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @return An ArrayList of Image Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Image> fetchImagesAt(String column, String val) throws Exception {
        ArrayList<Image> results = new ArrayList<>();

        String sql = "SELECT * FROM images WHERE " + column + " = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

        return results;
    }

    /**
     * Fetches all entries from the Managers Database
     * @return An ArrayList of Managers Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Manager> fetchManagers() throws Exception {
        ArrayList<Manager> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT acc.accountID, acc.firstName, "+
        "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
        "acc.shippingAddress, emp.totalSales, man.managerstatus " +
        "FROM ManagerAccount man "+
        "JOIN EmployeeAccount emp ON man.managerAccountID = emp.employeeAccountID "+
        "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID;";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

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

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Manager Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Manager> fetchManagersAt(String column, String val, String sign) throws Exception {
        ArrayList<Manager> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT acc.accountID, acc.firstName, "+
        "acc.lastName, acc.phone, acc.accountPassword, acc.email," +
        "acc.shippingAddress, emp.totalSales, man.managerstatus " +
        "FROM ManagerAccount man "+
        "JOIN EmployeeAccount emp ON man.managerAccountID = emp.employeeAccountID "+
        "JOIN Accounts acc ON emp.employeeAccountID = acc.accountID " +
        "WHERE "+column+" "+sign+" ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

        return results;
    }

    /**
     * Fetches all entries from the Sales Database
     * @return An ArrayList of Sale Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Sale> fetchSales() throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        ArrayList<Sale> results = new ArrayList<>();

        String sql = "SELECT * FROM Sale";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

        ResultSet rs1 = stmt.executeQuery();

        while (rs1.next()) {
            int vid = rs1.getInt("vehicleID");
            int eid = rs1.getInt("employeeAccountID");
            int cid = rs1.getInt("customerAccountID");
            results.add(new Sale(
                    rs1.getInt("saleID"),
                    fetchVehicleDataAt("vehicleID", vid+"", "=").getFirst(),
                    fetchEmployeeAt("employeeAccountID", eid+"", "=").getFirst(),
                    fetchCustomerAt("customerAccountID", cid+"", "=").getFirst(),
                    rs1.getDate("dateOFSale"),
                    rs1.getInt("amountPaid")));
        }

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Sale Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Sale> fetchSalesAt(String column, String val, String sign) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        ArrayList<Sale> results = new ArrayList<>();

        String sql = "SELECT * FROM Sale WHERE " + column + " " +sign +" ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
                    fetchVehicleDataAt("vehicleID", vid+"", "=").getFirst(),
                    fetchEmployeeAt("employeeAccountID", eid+"", "=").getFirst(),
                    fetchCustomerAt("customerAccountID", cid+"", "=").getFirst(),
                    rs1.getDate("dateOFSale"),
                    rs1.getInt("amountPaid")));
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
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM Service";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

        ResultSet rs1 = stmt.executeQuery();

        while (rs1.next()) {
            int vid = rs1.getInt("vehicleID");
            results.add(new Service(
                    rs1.getInt("serviceID"),
                    fetchVehicleDataAt("vehicleID", ""+vid, "=").getFirst(),
                    rs1.getDate("dateOfService"),
                    rs1.getString("descriptionOFService"),
                    rs1.getInt("cost"),
                    rs1.getInt("mileage")));
        }

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Service Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Service> fetchServiceAt(String column, String val, String sign) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        ArrayList<Service> results = new ArrayList<>();

        String sql = "SELECT * FROM Service WHERE " + column + " "+sign+" ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
                    fetchVehicleDataAt("vehicleID", ""+vid, "=").getFirst(),
                    rs1.getDate("dateOfService"),
                    rs1.getString("descriptionOFService"),
                    rs1.getInt("cost"),
                    rs1.getInt("mileage")));
        }

        return results;
    }

    /**
     * Fetches all entries from the Vehicle Database
     * @return An ArrayList of Vehicle Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Vehicle> fetchVehicleData() throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        ArrayList<Vehicle> results = new ArrayList<>();
        String sql = "SELECT * FROM vehicleData";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

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

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Vehicle Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Vehicle> fetchVehicleDataAt(String column, String val, String sign) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        ArrayList<Vehicle> results = new ArrayList<>();
        String sql = "SELECT * FROM vehicleData WHERE " + column + " " + sign + " ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
                stmt.setBoolean(1, val.equals("true"));
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

        return results;
    }

    // NOTE: Dates are always in the format of yyyy-mm-dd
    /**
     * Fetches all entries from the Accidents Database
     * @return An ArrayList of Accident Objects from the database
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Appointment> fetchAppointments() throws Exception {
        ArrayList<Appointment> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM Appointment";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int eid = rs.getInt("employeeAccountID");
            int cid = rs.getInt("customerAccountID");
            results.add(new Appointment(
                    rs.getInt("appointmentID"),
                    fetchEmployeeAt("accountID",""+eid,"=").get(0),
                    fetchCustomerAt("accountID",""+cid,"=").get(0),
                    rs.getDate("appointmentDate"),
                    rs.getString("typeOfAppointment")
                    ));
        }

        return results;
    }

    /**
     * Fetches entries that match a specific criteria
     * @param column the type of data you want to fetch
     * @param val the criteria that you want
     * @param sign the comparitor that will match the data with val
     * @return An ArrayList of Accident Objects from the database 
     * @throws Exception Failure to fetch
     */
    public static ArrayList<Appointment> fetchAppointmentssAt(String column, String val, String sign) throws Exception {
        ArrayList<Appointment> results = new ArrayList<>();
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "SELECT * FROM Appointment WHERE " + column + " "+ sign +" ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        switch (column) {
            case "appointmentID":
            case "employeeAccountID":
            case "customerAccountID":
                stmt.setInt(1, Integer.parseInt(val));
                break;
            case "apointmentDate":
                stmt.setDate(1, Date.valueOf(val));
                break;
            case "typeOfAppointment":
                stmt.setString(1, val);
                break;
            default:
                stmt.setInt(1, Integer.parseInt(val));
                break;
        }
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int eid = rs.getInt("employeeAccountID");
            int cid = rs.getInt("customerAccountID");
            results.add(new Appointment(
                    rs.getInt("appointmentID"),
                    fetchEmployeeAt("accountID",""+eid,"=").get(0),
                    fetchCustomerAt("accountID",""+cid,"=").get(0),
                    rs.getDate("apointmentDate"),
                    rs.getString("typeOfAppointment")
                    ));
        }

        return results;
    }

    // Inserting
    /**
     * Inserts a new entry to the Accident Database
     * @param n the accident entry
     * @throws Exception Failure to add, prints "false"
     */
    public static void InsertAccident(Accident n) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "INSERT INTO AccidentData (vehicleID, dateOfAccident, severity, descOfAccident, airbagDeployment) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, n.getVehicle().getVehicleID());
        stmt.setDate(2, n.getDateOfAccident());
        stmt.setString(3, n.getSeverity());
        stmt.setString(4, n.getDescription());
        stmt.setBoolean(5, n.isAirbagDeployment());
        stmt.executeUpdate();
    }

    /**
     * Inserts a new entry to the Account Database
     * NOTE: PLEASE DO NOT USE INSERTACCOUNT, USE INSERTCUSTOMER, INSERTMANAGER, and INSERTEMPLOYEE
     *       THOSE METHODS ALREADY RECURSIVELY CALL THIS METHOD, and also its more proper if we do 
     *       it that way
     * @param n the account entry
     * @throws Exception Failure to add, prints "false"
     */
    public static void InsertAccount(Account n) throws Exception {
       if(mDBConnection == null){
        initDatabaseConnection();
       }

            String sql = "INSERT INTO Accounts (firstName, lastName, phone, accountPassword, email, shippingAddress) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = mDBConnection.prepareStatement(sql);
            stmt.setString(1, n.getFirstName());
            stmt.setString(2, n.getLastName());
            stmt.setString(3, n.getPhoneNum());
            stmt.setString(4, n.getPassword()); 
            stmt.setString(5, n.getEmail());
            stmt.setString(6, n.getShippingAddress());
            stmt.executeUpdate();
        
        }

    /**
     * Inserts a new entry to the Customer Database
     * @param n the customer entry
     * @throws Exception Failure to add, prints "false"
     */
    public static void InsertCustomer(Customer n) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        InsertAccount(new Account(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), n.getPhoneNum(), n.getShippingAddress(), n.getPassword()));
        ArrayList<Account> temp = fetchAccountsAt("email", n.getEmail());
        String sql = "INSERT INTO CustomerAccount (customerAccountID) VALUES (?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, temp.getFirst().getAccountID());
        stmt.executeUpdate();
    }

    /**
     * Inserts a new entry to the Damage Database
     * @param n the damage entry
     * @throws Exception Failure to add
     */
    public static void InsertDamage(Damage n) throws Exception {
        String sql = "INSERT INTO damage (vehicleID, locationOfDamage, severity, repairCost, accidentID, airbagDeployment) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, n.getVehicle().getVehicleID());
        stmt.setString(2, n.getDamageLocation());
        stmt.setString(3, n.getSeverity());
        stmt.setDouble(4, n.getRepairCost());
        stmt.setInt(5, n.getAccident().getAccidentID());
        stmt.setBoolean(6, n.getAccident().isAirbagDeployment());
        stmt.executeUpdate();
    }

    /**
     * Inserts a new entry to the Employee Database
     * @param n the employee entry
     * @throws Exception Failure to add
     */
    public static void InsertEmployee(Employee n) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        InsertAccount(new Account(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(),
                                     n.getPhoneNum(), n.getShippingAddress(), n.getPassword()));
        ArrayList<Account> temp = fetchAccountsAt("email", n.getEmail());
        String sql = "INSERT INTO EmployeeAccount (employeeAccountID, totalSales) VALUES (?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, temp.getFirst().getAccountID());
        stmt.setDouble(2, n.getTotalSalesPerMonth());
        stmt.executeUpdate();
    }

    /**
     * Inserts a new entry to the Image Database
     * @param n the Image entry
     * @throws Exception Failure to add
     */
    public static void InsertImage(Image n) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "INSERT INTO images (imageID, vehicleID, imageURL) VALUES (?, ?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, n.getImageID());
        stmt.setInt(2, n.getVehicleID());
        stmt.setString(3, n.getImagePath());
        stmt.executeUpdate();
    }

    /**
     * Inserts a new entry to the Manager Database
     * @param n the manager entry
     * @throws Exception Failure to add, prints "false"
     */
    public static void InsertManager(Manager n) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        InsertEmployee(new Employee(n.getAccountID(), n.getFirstName(), n.getLastName(), n.getEmail(), 
                            n.getPhoneNum(), n.getShippingAddress(), n.getTotalSalesPerMonth(), n.getPassword()));
        ArrayList<Account> temp = fetchAccountsAt("email", n.getEmail());
        String sql = "INSERT INTO ManagerAccount (managerAccountID, managerstatus) VALUES (?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, temp.getFirst().getAccountID());
        stmt.setString(2, n.getStatus());
        stmt.executeUpdate();
    }


    /**
     * Inserts a new entry to the Sale Database
     * @param n the Sale entry
     * @throws Exception Failure to add
     */
    public static void InsertSale(Sale n) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "INSERT INTO Sale (vehicleID, employeeAccountID, customerAccountID, dateOFSale, amountPaid) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, n.getVehicle().getVehicleID());
        stmt.setInt(2, n.getEmployeeAccount().getAccountID());
        stmt.setInt(3, n.getCustomerAccount().getAccountID());
        stmt.setDate(4, n.getSaleDate());
        stmt.setDouble(5, n.getSaleAmount());
        stmt.executeUpdate();
    }

    /**
     * Inserts a new entry to the Service Database
     * @param n the Service entry
     * @throws Exception Failure to add, prints "false"
     */
    public static void InsertService(Service n) throws Exception {
        String sql = "INSERT INTO Service (vehicleID, dateOfService, descriptionOFService, cost, mileage) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        stmt.setInt(1, n.getVehicle().getVehicleID());
        stmt.setDate(2, n.getDateOfService());
        stmt.setString(3, n.getDescription());
        stmt.setDouble(4, n.getCost());
        stmt.setInt(5, n.getMileageAtService());
        stmt.executeUpdate();
    }

    /**
     * Inserts a new entry to the Vehicle Database
     * @param n the vehicle entry
     * @throws Exception Failure to add, prints "false"
     */
    public static void InsertVehicle(Vehicle n) throws Exception {
        String sql = "INSERT INTO vehicleData (vinNumber, price, maker, model, color, modelYear, bodyStyle, isUsed, mileage, carStatus, prevOwnerCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
    }

    /**
     * Inserts a new entry to the Account Database
     * NOTE: PLEASE DO NOT USE INSERTACCOUNT, USE INSERTCUSTOMER, INSERTMANAGER, and INSERTEMPLOYEE
     *       THOSE METHODS ALREADY RECURSIVELY CALL THIS METHOD, and also its more proper if we do 
     *       it that way
     * @param n the account entry
     * @throws Exception Failure to add, prints "false"
     */
    public static void InsertAppointment(Appointment n) throws Exception {
       if(mDBConnection == null){
        initDatabaseConnection();
       }
            String sql = "INSERT INTO Appointment (employeeAccountID, customerAccountID, apointmentDate, typeOfAppointment) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = mDBConnection.prepareStatement(sql);
            stmt.setInt(1, n.getEmployeeAccountID().getAccountID());
            stmt.setInt(2, n.getCustomerAccountID().getAccountID());
            stmt.setDate(3, n.getAppointmentDate());
            stmt.setString(4, n.getTypeOfAppointment()); 
            stmt.executeUpdate();
        
        }

    //Update Methods
    /**
     * Updates entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateAccidents(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE AccidentData SET " + column+ " = ? WHERE accidentID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
                stmt.setBoolean(1, val.equals("true"));
                break;
            default:
                stmt.setInt(1, Integer.parseInt(val));
                break;
        }
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    /**
     * Updates general account entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateAccount(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE Accounts SET " + column+ " = ? WHERE accountID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

    /**
     * Updates vehicle damage entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateDamage(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE damage SET " + column+ " = ? WHERE damageID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

    /**
     * Updates employee account entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateEmployee(int id, String column, String val) throws Exception {
        String sql = "UPDATE EmployeeAccount SET " + column+ " = ? WHERE employeeAccountID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

    /**
     * Updates image entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateImages(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE images SET " + column+ " = ? WHERE imageID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

    /**
     * Updates manager entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database occurred.
     */
    public static void updateManager(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE ManagerAccount SET " + column+ " = ? WHERE managerAccountID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

    /**
     * Updates sale entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateSale(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }
        String sql = "UPDATE Sale SET " + column+ " = ? WHERE saleID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

    /**
     * Updates service entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateService(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE Service SET " + column+ " = ? WHERE serviceID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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

    /**
     * Updates vehicle data entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateVehicleData(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE vehicleData SET " + column+ " = ? WHERE vehicleID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
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
                stmt.setBoolean(1, val.equals("true"));
                break;
        }

        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    /**
     * Updates vehicle data entries in the table
     * @param id the id
     * @param column column
     * @param val value
     * @throws Exception An issue with the database connection occurred.
     */
    public static void updateAppointment(int id, String column, String val) throws Exception {
        if (mDBConnection == null) {
            initDatabaseConnection();
        }

        String sql = "UPDATE Appointment SET " + column+ " = ? WHERE appointmentID = ?";
        PreparedStatement stmt = mDBConnection.prepareStatement(sql);
        switch (column) {
            case "employeeAccountID":
            case "customerAccountID":
                stmt.setInt(1, Integer.parseInt(val));
                break;
            case "apointmentDate":
                stmt.setDate(1, Date.valueOf(val));
                break;
            case "typeOfAppointment":
                stmt.setString(1, val);
                break;
            default:
                stmt.setInt(1, Integer.parseInt(val));
                break;
        }

        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
}