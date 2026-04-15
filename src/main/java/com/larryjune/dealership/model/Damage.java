package com.larryjune.dealership.model;

import java.sql.Date;

public class Damage {
    private int damageID;
    private int vehicleID;
    private Date dateOfDamage;
    private String damageLocation;
    private String severity;
    private double repairCost;
    private int accidentID;

    public Damage(int damageID, int vehicleID, Date dateOfDamage, String damageLocation, String severity,
            double repairCost, int accidentID) {
        this.damageID = damageID;
        this.vehicleID = vehicleID;
        this.dateOfDamage = dateOfDamage;
        this.damageLocation = damageLocation;
        this.severity = severity;
        this.repairCost = repairCost;
        this.accidentID = accidentID;
    }

    public int getDamageID() {
        return damageID;
    }

    public void setDamageID(int damageID) {
        this.damageID = damageID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
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

    public int getAccidentID() {
        return accidentID;
    }

    public void setAccidentID(int accidentID) {
        this.accidentID = accidentID;
    }
}
