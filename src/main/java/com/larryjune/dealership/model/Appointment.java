package com.larryjune.dealership.model;

import java.sql.Date;

public class Appointment {
    private int appointmentID;
    private Employee employeeAccountID;
    private Customer customerAccountID;
    private Date appointmentDate;
    private String typeOfAppointment;

    public Appointment(int appointmentID, Employee employeeAccountID, Customer customerAccountID, Date appointmentDate, String typeOfAppointment) {
        this.appointmentID = appointmentID;
        this.employeeAccountID = employeeAccountID;
        this.customerAccountID = customerAccountID;
        this.appointmentDate = appointmentDate;
        this.typeOfAppointment = typeOfAppointment;
    }

    public Appointment(Employee employeeAccountID, Customer customerAccountID, Date appointmentDate, String typeOfAppointment) {
        this.employeeAccountID = employeeAccountID;
        this.customerAccountID = customerAccountID;
        this.appointmentDate = appointmentDate;
        this.typeOfAppointment = typeOfAppointment;
    }

    public Employee getEmployeeAccountID() {
        return this.employeeAccountID;
    }

    public void setEmployeeAccountID(Employee newEmployeeId) {
        this.employeeAccountID = newEmployeeId;
    }

    public Customer getCustomerAccountID() {
        return this.customerAccountID;
    }

    public void setCustomerAccountID(Customer newCustomerId) {
        this.customerAccountID = newCustomerId;
    }

    public Date getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(Date newDate) {
        this.appointmentDate = newDate;
    }

    public String getTypeOfAppointment() {
        return this.typeOfAppointment;
    }

    public void setTypeOfAppointment(String newType) {
        this.typeOfAppointment = newType;
    }



    public int getAppointmentID() {
        return appointmentID;
    }



    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
}
