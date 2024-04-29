/* 
ContractFileManager.java
This Java file allows the program to write the information into the contracts.csv file.
*/

package com.yearup.dealership;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    // Create the variable.
    private static final String contractsFile = "contracts.csv";

    // Create the saveContract method.
    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(contractsFile, true)) {
            // Use sale if sale, use lease if lease.
            String contractType = (contract instanceof SalesContract) ? "SALE" : "LEASE";

            // Write the information to the file.
            writer.write(contractType + "|" +
                    contract.getContractDate() + "|" +
                    contract.getCustomerName() + "|" +
                    contract.getCustomerEmail() + "|" +
                    contract.getVehicleSold().getVin() + "|" +
                    contract.getVehicleSold().getYear() + "|" +
                    contract.getVehicleSold().getMake() + "|" +
                    contract.getVehicleSold().getModel() + "|" +
                    contract.getVehicleSold().getVehicleType() + "|" +
                    contract.getVehicleSold().getColor() + "|" +
                    contract.getVehicleSold().getOdometer() + "|" +
                    contract.getVehicleSold().getPrice() + "|" +
                    contract.getTotalPrice() + "|" +
                    (contract instanceof SalesContract ? ((SalesContract) contract).getSalesTax() : "0.00") + "|" +
                    (contract instanceof SalesContract ? ((SalesContract) contract).getRecordingFee() : "0.00") + "|" +
                    (contract instanceof SalesContract ? ((SalesContract) contract).getProcessingFee() : "0.00") + "|" +
                    (contract instanceof LeaseContract ? ((LeaseContract) contract).getLeaseFee() : "0.00") + "|" +
                    (contract instanceof SalesContract ? (((SalesContract) contract).isFinanceOption() ? "YES" : "NO") : "NO") + "|" +
                    (contract instanceof LeaseContract ? String.format("%.2f", ((LeaseContract) contract).getMonthlyPayment()) : "0.00") + "\n");

            writer.flush();
        // Print error if invalid input.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}