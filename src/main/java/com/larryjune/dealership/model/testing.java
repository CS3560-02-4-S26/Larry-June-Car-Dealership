package com.larryjune.dealership.model;

import java.util.ArrayList;
import java.sql.Date;

public class testing {
    public static void main(String[] args) throws Exception {
        ArrayList<Sale> s = DBControl.fetchSales();
        Vehicle v = DBControl.fetchVehicleDataAt("vehicleID", "3", "=").get(0);
        ArrayList<Customer> c = DBControl.fetchCustomer();
        ArrayList<Employee> e = DBControl.fetchEmployee();
        for(int i = 0; i < s.size(); i++){
            System.out.println(s.get(i));
        }
        System.out.println(DBControl.InsertSale(new Sale(0, v, e.get(0), c.get(0), Date.valueOf("2026-01-08"), v.getPrice())));
    }
}
