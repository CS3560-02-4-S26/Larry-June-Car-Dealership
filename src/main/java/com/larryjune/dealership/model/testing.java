package com.larryjune.dealership.model;

import java.util.ArrayList;
import java.sql.Date;

public class testing {
    public static void main(String[] args) throws Exception {
        DBControl.InsertManager(new Manager(0, "Blue King","Clash Royale", "clashroyaleblueking@gmail.com", "1-676-7676", "Rich", 0.1, "definitly here" ,"idk"));
        System.out.println("OK");
    }
}
