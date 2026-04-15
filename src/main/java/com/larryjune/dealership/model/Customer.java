package com.larryjune.dealership.model;

public class Customer extends Account {

    public Customer(int accountID, String firstName, String lastName, String email, String phoneNum,
            String shippingAddress) {
        super(accountID, firstName, lastName, email, phoneNum, shippingAddress);
    }
}
