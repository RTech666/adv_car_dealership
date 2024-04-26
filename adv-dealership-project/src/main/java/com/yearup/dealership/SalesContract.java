package com.yearup.dealership;

public class SalesContract extends Contract {
    // Create the variables, as private.
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean financeOption;
    static final double salesTaxRate = 0.05;
    static final double recordingFeeRate = 100.0;
    static final double feeUnder10K = 295.0;
    static final double feeOver10K = 495.0;

    // Create the contructor.
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, double salesTax, double recordingFee, double processingFee) {
        super(date, customerName, customerEmail, vehicle);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
    }

    // Create getter and setters.
    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    public double getSalesTax() {
        return this.salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = getVehicleSold().getPrice();
        double salesTax = totalPrice * salesTaxRate;
        double processingFee = (totalPrice < 10000) ? feeUnder10K : feeOver10K;
        return totalPrice + salesTax + recordingFeeRate + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (financeOption) {
            double totalPrice = getTotalPrice();
            double interestRate = (totalPrice >= 10000) ? 0.0425 : 0.0525;
            int loanTerm = (totalPrice >= 10000) ? 48 : 24;
            return (totalPrice * interestRate) / (1 - Math.pow(1 + interestRate, -loanTerm));
        } else {
            return 0;
        }
    }
}
