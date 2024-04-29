/* 
LeaseContract.java
This Java file contains the constructors, getters, and setters for the vehicle sales contract.

getTotalPrice() - Calulates the total price of the vehicle including the lease fee.
*/

package com.yearup.dealership;

public class LeaseContract extends Contract {
    // Create the variables.
    private double expectedEndingValue;
    private double leaseFee;
    private double monthlyPayment;
    public static final double leaseFeeRate = 0.07;
    public static final double interestRate = 0.04;
    public static final int loanTerm = 36;

    // Create the constructor.
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle, double leaseFee, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicle);
        this.leaseFee = leaseFee;
        this.monthlyPayment = monthlyPayment;
    }

    // Create getters and setters.
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + leaseFee;
    }

    @Override
    public boolean isFinanceOption() {
        return false;
    }
}
