package com.larryjune.dealership.model;

import java.sql.Date;

public class Service {
    private int serviceID;
    private int vehicleID;
    private Date dateOfService;
    private String description;
    private double cost;
    private int mileageAtService;

    public Service(int serviceID, int vehicleID, Date dateOfService, String description, double cost,
            int mileageAtService) {
        this.serviceID = serviceID;
        this.vehicleID = vehicleID;
        this.dateOfService = dateOfService;
        this.description = description;
        this.cost = cost;
        this.mileageAtService = mileageAtService;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Date getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(Date dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMileageAtService() {
        return mileageAtService;
    }

    public void setMileageAtService(int mileageAtService) {
        this.mileageAtService = mileageAtService;
    }
}
