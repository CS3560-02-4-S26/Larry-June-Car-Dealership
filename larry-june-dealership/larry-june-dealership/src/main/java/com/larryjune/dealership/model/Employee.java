package com.larryjune.dealership.model;

public class Employee extends Account {
    private double totalSalesPerMonth;

    public Employee(int accountID, String firstName, String lastName, String email, String phoneNum,
            String shippingAddress, double totalSalesPerMonth) {
        super(accountID, firstName, lastName, email, phoneNum, shippingAddress);
        this.totalSalesPerMonth = totalSalesPerMonth;
    }

    public double getTotalSalesPerMonth() {
        return totalSalesPerMonth;
    }

    public void setTotalSalesPerMonth(double totalSalesPerMonth) {
        this.totalSalesPerMonth = totalSalesPerMonth;
    }
}
