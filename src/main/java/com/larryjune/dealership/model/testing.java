package com.larryjune.dealership.model;

import java.util.ArrayList;
import java.sql.Date;

public class testing {
    public static void main(String[] args) throws Exception {
        System.out.println(DBControl.InsertCustomer(new Customer(0, "Mega Knight","Clash ROyale", "heheheheha@gmail.com", "1-800-1234", "Midladder", "Jynxzi Sucks at Clash")));
    }
}
