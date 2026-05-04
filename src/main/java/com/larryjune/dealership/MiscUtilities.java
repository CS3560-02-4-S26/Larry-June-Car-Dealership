package com.larryjune.dealership;

public class MiscUtilities {
    public static String mapCarStatus(String status) {
        switch(status) {
            case "1":
                return "Available";

            case "2":
                return "In service";

            case "0":
                return "In repair";

            default:
                return "Unknown";
        }
    }
}
