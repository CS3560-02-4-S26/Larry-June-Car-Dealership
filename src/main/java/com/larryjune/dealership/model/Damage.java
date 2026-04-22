package com.larryjune.dealership.model;

import java.sql.Date;

public class Damage {
    private int damageID;
    private Vehicle vehicle;
    private Date dateOfDamage;
    private String damageLocation;
    private String severity;
    private double repairCost;
    private Accident accident;

    public Damage(int damageID, Vehicle vehicle, Date dateOfDamage, String damageLocation, String severity,
            double repairCost, Accident accident) {
        this.damageID = damageID;
        this.vehicle = vehicle;
        this.dateOfDamage = dateOfDamage;
        this.damageLocation = damageLocation;
        this.severity = severity;
        this.repairCost = repairCost;
        this.accident = accident;
    }

    public int getDamageID() {
        return damageID;
    }

    public void setDamageID(int damageID) {
        this.damageID = damageID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDateOfDamage() {
        return dateOfDamage;
    }

    public void setDateOfDamage(Date dateOfDamage) {
        this.dateOfDamage = dateOfDamage;
    }

    public String getDamageLocation() {
        return damageLocation;
    }

    public void setDamageLocation(String damageLocation) {
        this.damageLocation = damageLocation;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public Accident getAccident() {
        return accident;
    }

    public void setAccident(Accident accident) {
        this.accident = accident;
    }

    @Override
    public String toString(){
        return "{"+ damageID + ", " + vehicle + ", " + dateOfDamage + ", " + damageLocation + ", " + severity + ", " + repairCost + ", " + accident +"}";
    }
}
