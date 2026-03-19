package com.larryjune.dealership.model;

import java.sql.Date;

public class Sale {
    private int saleID;
    private int vehicleID;
    private int employeeAccountID;
    private int customerAccountID;
    private Date saleDate;
    private double saleAmount;

    public Sale(int saleID, int vehicleID, int employeeAccountID, int customerAccountID, Date saleDate,
            double saleAmount) {
        this.saleID = saleID;
        this.vehicleID = vehicleID;
        this.employeeAccountID = employeeAccountID;
        this.customerAccountID = customerAccountID;
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getEmployeeAccountID() {
        return employeeAccountID;
    }

    public void setEmployeeAccountID(int employeeAccountID) {
        this.employeeAccountID = employeeAccountID;
    }

    public int getCustomerAccountID() {
        return customerAccountID;
    }

    public void setCustomerAccountID(int customerAccountID) {
        this.customerAccountID = customerAccountID;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }
}
