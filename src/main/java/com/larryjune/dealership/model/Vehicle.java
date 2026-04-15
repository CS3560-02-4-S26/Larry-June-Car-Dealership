package com.larryjune.dealership.model;

public class Vehicle {
    private int vehicleID;
    private String vinNumber;
    private double price;
    private String make;
    private String model;
    private String color;
    private int year;
    private String bodyStyle;
    private boolean isUsed;
    private int mileage;
    private String carStatus;
    private int previousOwnerCount;

    public Vehicle(int vehicleID, String vinNumber, double price, String make, String model, String color, int year,
            String bodyStyle, boolean isUsed, int mileage, String carStatus, int previousOwnerCount) {
        this.vehicleID = vehicleID;
        this.vinNumber = vinNumber;
        this.price = price;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.bodyStyle = bodyStyle;
        this.isUsed = isUsed;
        this.mileage = mileage;
        this.carStatus = carStatus;
        this.previousOwnerCount = previousOwnerCount;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public int getPreviousOwnerCount() {
        return previousOwnerCount;
    }

    public void setPreviousOwnerCount(int previousOwnerCount) {
        this.previousOwnerCount = previousOwnerCount;
    }
}
