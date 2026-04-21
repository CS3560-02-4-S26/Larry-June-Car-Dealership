package com.larryjune.dealership.model;

import java.sql.Date;

public class Accident {
    private int accidentID;
    private Vehicle vehicle;
    private Date dateOfAccident;
    private String severity;
    private boolean airbagDeployment;
    private String description;

    public Accident(int accidentID, Vehicle vehicle, Date dateOfAccident, String severity, boolean airbagDeployment,
            String description) {
        this.accidentID = accidentID;
        this.vehicle = vehicle;
        this.dateOfAccident = dateOfAccident;
        this.severity = severity;
        this.airbagDeployment = airbagDeployment;
        this.description = description;
    }

    public int getAccidentID() {
        return accidentID;
    }

    public void setAccidentID(int accidentID) {
        this.accidentID = accidentID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDateOfAccident() {
        return dateOfAccident;
    }

    public void setDateOfAccident(Date dateOfAccident) {
        this.dateOfAccident = dateOfAccident;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean isAirbagDeployment() {
        return airbagDeployment;
    }

    public void setAirbagDeployment(boolean airbagDeployment) {
        this.airbagDeployment = airbagDeployment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "{" + accidentID + ", " + vehicle + ", " + dateOfAccident + ", " + severity + ", " + airbagDeployment + ", " + description + "}";
    }
}
