package com.larryjune.dealership.model;

public class Appointment {
    private int employeeAccountID;
    private int customerAccountID;
    private String appointmentDate;
    private String typeOfAppointment;

    private Appointment(int employeeAccountID, int customerAccountID, String appointmentDate, String typeOfAppointment) {
        this.employeeAccountID = employeeAccountID;
        this.customerAccountID = customerAccountID;
        this.appointmentDate = appointmentDate;
        this.typeOfAppointment = typeOfAppointment;
    }

    public int getEmployeeAccountID() {
        return this.employeeAccountID;
    }

    public void setEmployeeAccountID(int newEmployeeId) {
        this.employeeAccountID = newEmployeeId;
    }

    public int getCustomerAccountID() {
        return this.customerAccountID;
    }

    public void setCustomerAccountID(int newCustomerId) {
        this.customerAccountID = newCustomerId;
    }

    public String getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(String newDate) {
        this.appointmentDate = newDate;
    }

    public String getTypeOfAppointment() {
        return this.typeOfAppointment;
    }

    public void setTypeOfAppointment(String newType) {
        this.typeOfAppointment = newType;
    }
}
