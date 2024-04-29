/* 
AdminUserInterface.java
This Java file contains the admin menu and anything related to admin options.

displayMenu() - Prints the admin menu and asks user what they want to do.
listLast10Contracts() - Prints last 10 contracts from contracts.csv.
listAllContracts() - Print all contracts from contracts.csv.
*/

package com.yearup.dealership;
import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    // Initalize the scanner.
    static Scanner scanner = new Scanner(System.in);

    // Initalize the variable.
    private ContractDataManager contractDataManager;

    // Create the contrsuctor.
    public AdminUserInterface(ContractDataManager contractDataManager) {
        this.contractDataManager = contractDataManager;
    }

    public void displayMenu() {
        // Print the admin menu.
        System.out.println("Admin Menu:");
        System.out.println("1. View last 10 contracts");
        System.out.println("2. View all contracts");
        System.out.println("3. Back to main menu");
    
        // Ask user what choice they want.
        System.out.print("Enter your choice: ");
        int choice;

        try {
            choice = scanner.nextInt();
            // Read the user input and execute the appropriate method.
            switch (choice) {
                case 1:
                    listLast10Contracts();
                    break;
                case 2:
                    listAllContracts();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
                    displayMenu();
                    return;
            }
        // Print error if invalid input.
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid option.");
            scanner.next();
            displayMenu();
        }
    }

    public void listLast10Contracts() {
        // Initalize the variable.
        List<String> last10Contracts = contractDataManager.getLast10Contracts();

        // Print last 10 contracts.
        System.out.println("\nLast 10 Contracts:");
        for (String contract : last10Contracts) {
            System.out.println(contract);
        }

        // Return to admin menu.
        displayMenu();
    }

    // Create the listAllContracts method.
    public void listAllContracts() {
        // Initalize the variable.
        List<String> contracts = contractDataManager.getAllContracts();

        // Print all contracts.
        System.out.println("\nAll Contracts:");
        for (String contract : contracts) {
            System.out.println(contract);
        }

        // Return to admin menu.
        displayMenu();
    }
}