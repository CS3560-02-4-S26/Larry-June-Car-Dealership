package com.larryjune.dealership.model;

public class FinancingAgreement {
    private int financingID;
    private int saleID;
    private double monthlyPayment;
    private double interest;
    private double lateAPR;

    public FinancingAgreement(int financingID, int saleID, double monthlyPayment, double interest, double lateAPR) {
        this.financingID = financingID;
        this.saleID = saleID;
        this.monthlyPayment = monthlyPayment;
        this.interest = interest;
        this.lateAPR = lateAPR;
    }

    public int getFinancingID() {
        return financingID;
    }

    public void setFinancingID(int financingID) {
        this.financingID = financingID;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getLateAPR() {
        return lateAPR;
    }

    public void setLateAPR(double lateAPR) {
        this.lateAPR = lateAPR;
    }
}
