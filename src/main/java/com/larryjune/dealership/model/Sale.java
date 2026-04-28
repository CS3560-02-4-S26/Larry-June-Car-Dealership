package com.larryjune.dealership.model;

import java.sql.Date;

public class Sale {
    private int saleID;
    private Vehicle vehicle;
    private Employee employeeAccount;
    private Customer customerAccount;
    private Date saleDate;
    private double saleAmount;

    public Sale(int saleID, Vehicle vehicle, Employee employeeAccount, Customer customerAccount, Date saleDate,
            double saleAmount) {
        this.saleID = saleID;
        this.vehicle = vehicle;
        this.employeeAccount = employeeAccount;
        this.customerAccount = customerAccount;
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicleID(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(Employee employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public Customer getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Customer customerAccount) {
        this.customerAccount = customerAccount;
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

    @Override
    public String toString(){
        return "{" + saleID + ", " + vehicle + ", " + employeeAccount + ", " + customerAccount + ", " + saleDate + ", " + saleAmount + "}";
    }
}
