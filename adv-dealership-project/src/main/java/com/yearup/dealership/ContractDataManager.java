package com.yearup.dealership;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    // Create the variable.
    private static final String contractsFile = "contracts.csv";

    // Create the saveContract method.
    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(contractsFile, true)) {
            // Write information if its a sale.
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                writer.write("SALE|" + salesContract.getContractDate() + "|" +
                        salesContract.getCustomerName() + "|" +
                        salesContract.getCustomerEmail() + "|" +
                        salesContract.getVehicleSold().getVin() + "|" +
                        salesContract.getVehicleSold().getYear() + "|" +
                        salesContract.getVehicleSold().getMake() + "|" +
                        salesContract.getVehicleSold().getModel() + "|" +
                        salesContract.getVehicleSold().getVehicleType() + "|" +
                        salesContract.getVehicleSold().getColor() + "|" +
                        salesContract.getVehicleSold().getOdometer() + "|" +
                        salesContract.getVehicleSold().getPrice() + "|" +
                        salesContract.getTotalPrice() + "|" +
                        salesContract.getSalesTax() + "|" +
                        salesContract.getRecordingFee() + "|" +
                        salesContract.getProcessingFee() + "|" +
                        (salesContract.isFinanceOption() ? "YES" : "NO") + "|" +
                        salesContract.getMonthlyPayment() + "\n");
            // Write information if its a lease.
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                writer.write("LEASE|" + leaseContract.getContractDate() + "|" +
                        leaseContract.getCustomerName() + "|" +
                        leaseContract.getCustomerEmail() + "|" +
                        leaseContract.getVehicleSold().getVin() + "|" +
                        leaseContract.getVehicleSold().getYear() + "|" +
                        leaseContract.getVehicleSold().getMake() + "|" +
                        leaseContract.getVehicleSold().getModel() + "|" +
                        leaseContract.getVehicleSold().getVehicleType() + "|" +
                        leaseContract.getVehicleSold().getColor() + "|" +
                        leaseContract.getVehicleSold().getOdometer() + "|" +
                        leaseContract.getVehicleSold().getPrice() + "|" +
                        leaseContract.getTotalPrice() + "|" +
                        leaseContract.getExpectedEndingValue() + "|" +
                        leaseContract.getLeaseFee() + "|" +
                        (leaseContract.isFinanceOption() ? "YES" : "NO") + "|" +
                        leaseContract.getMonthlyPayment() + "\n");
            }

            writer.flush();
        // Print error if invalid input.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}