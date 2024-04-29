/* 
Contract.java
This Java file contains the constructors, getters, and abstract methods for the vehicle contracts.
*/

package com.yearup.dealership;

public abstract class Contract {
    // Create the veriables, as private.
    private String contractDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    // Create the contructor.
    public Contract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Create the getters and setters.
    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    // Create the abstract methods.
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract boolean isFinanceOption();
}