package com.larryjune.dealership.model;

public class Employee extends Account {
    private double totalSalesPerMonth;

    public Employee(int accountID, String firstName, String lastName, String email, String phoneNum,
            String shippingAddress, double totalSalesPerMonth, String password) {
        super(accountID, firstName, lastName, email, phoneNum, shippingAddress, password);
        this.totalSalesPerMonth = totalSalesPerMonth;
    }

    public double getTotalSalesPerMonth() {
        return totalSalesPerMonth;
    }

    public void setTotalSalesPerMonth(double totalSalesPerMonth) {
        this.totalSalesPerMonth = totalSalesPerMonth;
    }
}
