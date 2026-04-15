package com.larryjune.dealership.model;

public class Manager extends Employee {
    private String status;

    public Manager(int accountID, String firstName, String lastName, String email, String phoneNum,
            String shippingAddress, double totalSalesPerMonth, String status) {
        super(accountID, firstName, lastName, email, phoneNum, shippingAddress, totalSalesPerMonth);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
