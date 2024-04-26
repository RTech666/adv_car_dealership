package com.yearup.dealership;
import java.util.List;

public class AdminUserInterface {
    private ContractDataManager contractDataManager;

    public AdminUserInterface(ContractDataManager contractDataManager) {
        this.contractDataManager = contractDataManager;
    }

    public void display() {
        listAllContracts();
        listLast10Contracts();
    }

    public void listAllContracts() {
        List<String> contracts = contractDataManager.getAllContracts();
        System.out.println("All Contracts:");
        for (String contract : contracts) {
            System.out.println(contract);
        }
    }

    public void listLast10Contracts() {
        List<String> last10Contracts = contractDataManager.getLast10Contracts();
        System.out.println("Last 10 Contracts:");
        for (String contract : last10Contracts) {
            System.out.println(contract);
        }
    }
}