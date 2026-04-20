package com.larryjune.dealership.model;

import java.util.ArrayList;
import java.sql.Date;

public class testing {
    public static void main(String[] args) throws Exception {
        System.out.println(DBControl.InsertService(new Service(67, 6, Date.valueOf("2001-06-07"), "Mad cuz bad", 676767.0, 676767)));
    }
}
