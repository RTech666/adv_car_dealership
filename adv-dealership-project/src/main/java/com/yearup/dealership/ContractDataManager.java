/* 
ContractDataManager.java
This Java file allows the program to save the changes made while the program was running.
*/

package com.yearup.dealership;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContractDataManager {
    // Create the variable.
    private static final String contractsFile = "contracts.csv";

    // Create the getAllContracts method.
    public List<String> getAllContracts() {
        // Initalize the variable.
        List<String> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(contractsFile))) {
            String line;
            
            // Read all lines from the file.
            while ((line = reader.readLine()) != null) {
                contracts.add(line);
            }
        // Print error if invalid input.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    // Create the getLast10Contracts method.
    public List<String> getLast10Contracts() {
        // Initalize the variable.
        List<String> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(contractsFile))) {
            // Read all lines from the file.
            List<String> allContracts = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                allContracts.add(line);
            }

            // Get the last 10 contracts.
            int startIndex = Math.max(0, allContracts.size() - 10);
            for (int i = startIndex; i < allContracts.size(); i++) {
                contracts.add(allContracts.get(i));
            }
        // Print error if invalid input.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contracts;
    }

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