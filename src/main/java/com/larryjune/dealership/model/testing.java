package com.larryjune.dealership.model;

import java.util.ArrayList;

public class testing {
    public static void main(String[] args) throws Exception {
        ArrayList<Vehicle> s = DBControl.fetchVehicleData();
        for(int i = 0; i < s.size(); i++){
            Vehicle t = s.get(i);
            System.out.println(t.getYear() + " " + t.getMake() + " " + t.getModel());
        }
        System.out.println(DBControl.InsertVehicle(new Vehicle(0,"1877CAR",9999999,"Honda", "Accord", "White", 2029, "IDK", true, 90909, "Damn son", 1)));
    }
}
