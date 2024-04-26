/* 
UserInterface.java

This Java file is mainly just the face of the program. It calls the methods from other classes depnding on the user's choice and has error handling.

display() - Prints the main menu and asks the user what they want to do.
processGetAllVehiclesRequest() - Initalizes the array.
processGetByPriceRequest() - Asks the user for their price range and then reads the CSV for any vehicles that match the price range and prints the results.
processGetByMakeModelRequest() - Same as the previous method, but does it by make and model.
processGetByYearRequest() - Same as the previous method, but does it by year range.
processGetByColorRequest() - Same as the previous method, but does it by color.
processGetByMilageRequest() - Same as the previous method, but does it by mileage range.
processAddVehicleRequest() - Asks the user for the information of they car they want to add, then it adds it to the CSV.
processRemoveVehicleRequest() - Asks the user for the VIn number of the vehicle they want to remove from the CSV.
*/

package com.yearup.dealership;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    // Initalize the scanner.
    static Scanner scanner = new Scanner(System.in);

    // Create the variables.
    public Dealership dealership;
    public int choice;
    public DealershipFileManager dealershipFileManager;
    public List<Vehicle> vehicles;
    public List<Vehicle> vehiclesByYear;
    public List<Vehicle> vehiclesByColor;
    public List<Vehicle> vehiclesByMileage;
    public String vehicleType;
    public List<Vehicle> vehiclesByType;
    public Vehicle vehicle;
    public List<AddOn> selectedAddOns;

    // Create userInterface method.
    public UserInterface() {
        // Call the display method.
        display();
    }

    public void display() {
        init();
        while (true) {
            // Print menu options.
            System.out.println("\n--- Menu ---");
            System.out.println("1. Search by price");
            System.out.println("2. Search by make and model");
            System.out.println("3. Search by year");
            System.out.println("4. Search by color");
            System.out.println("5. Search by mileage");
            System.out.println("6. Search by vehicle type");
            System.out.println("7. List all vehicles");
            System.out.println("8. Add a vehicle");
            System.out.println("9. Remove a vehicle");
            System.out.println("10. Sell/lease a vehicle");
            System.out.println("11. Admin");
            System.out.println("0. Exit");
    
            // Ask the user to enter their choice.
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
    
                // Read the user input and execute the appropriate method.
                switch (choice) {
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMilageRequest();
                        break;
                    case 6:
                        processGetByVehicleTypeRequest();
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        processAddVehicleRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 10:
                        sellOrLeaseVehicle();
                        break;
                    case 11:
                        adminLogin();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Input.");
                }
            // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the scanner buffer
            }
        }
    }

    // Create the private init method.
    private void init() {
        // Initalize the variable.
        dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();
    }

    // Create the private displayVehicles method.
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create the processGetAllVehiclesRequest method.
    public void processGetAllVehiclesRequest() {
        // Initalize the variable.
        vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    // Create the processGetByPriceRequest method.
    public void processGetByPriceRequest() {
        // Create the variables.
        double minPrice = 0;
        double maxPrice = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Ask user for the minimum price.
                System.out.print("Enter minimum price: ");
                minPrice = scanner.nextDouble();
                scanner.nextLine();
    
                // Ask user for the maximum price.
                System.out.print("Enter maximum price: ");
                maxPrice = scanner.nextDouble();
                scanner.nextLine();
                
                // Set validInput to true to stop the loop.
                validInput = true;
            // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid price.");
                // Clear the invalid input.
                scanner.nextLine();
            }
        }
    
        // Print title.
        System.out.println("\nSearch Results:");
    
        // Get vehicles by price range
        vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        
        // If nothing matches, print error.
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found within the specified price range.");
        } else {
            displayVehicles(vehicles);
        }
    }

    // Create the processGetByMakeModelRequest method.
    public void processGetByMakeModelRequest() {
        // Create the variables.
        String make = "";
        String model = "";
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Ask user for the make.
                System.out.print("Enter make: ");
                make = scanner.nextLine();
    
                // Ask user for the model.
                System.out.print("Enter model: ");
                model = scanner.nextLine();
                
                // Set validInput to true to stop the loop.
                validInput = true;
            // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid make and model.");
                // Clear the invalid input.
                scanner.nextLine();
            }
        }
    
        // Print title.
        System.out.println("\nSearch Results:");
    
        // Get vehicles by make and model
        vehicles = dealership.getVehiclesByMakeModel(make, model);
        
        // If nothing matches, print error.
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found with the specified make and model.");
        } else {
            displayVehicles(vehicles);
        }
    }

    // Create the processGetByYearRequest method.
    public void processGetByYearRequest() {
        // Create the variables.
        int minYear = 0;
        int maxYear = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Ask the user for the minimum year.
                System.out.print("Enter minimum year: ");
                minYear = scanner.nextInt();
    
                // Ask the user for the maximum year.
                System.out.print("Enter maximum year: ");
                maxYear = scanner.nextInt();
                
                // Set validInput to true to stop the loop.
                validInput = true;
            // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid year.");
                // Clear the invalid input.
                scanner.nextLine();
            }
        }
    
        // Print title.
        System.out.println("\nSearch Results:");
    
        // Get vehicles by year range
        vehiclesByYear = dealership.getVehiclesByYear(minYear, maxYear);
        
        // If nothing matches, print error.
        if (vehiclesByYear.isEmpty()) {
            System.out.println("No vehicles found within the specified year range.");
        } else {
            displayVehicles(vehiclesByYear);
        }
    }

    // Create the processGetByColorRequest method.
    public void processGetByColorRequest() {
        // Create the variables.
        String color = "";
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Ask the user for the color.
                System.out.print("Enter color: ");
                color = scanner.nextLine();
                
                // Set validInput to true to stop the loop.
                validInput = true;
            // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid color.");
                // Clear the invalid input.
                scanner.nextLine();
            }
        }
    
        // Print title.
        System.out.println("\nSearch Results:");
    
        // Get vehicles by color
        vehiclesByColor = dealership.getVehiclesByColor(color);
        
        // If nothing matches, print error.
        if (vehiclesByColor.isEmpty()) {
            System.out.println("No vehicles found with the specified color.");
        } else {
            displayVehicles(vehiclesByColor);
        }
    }

    // Create the processGetByMilageRequest method.
    public void processGetByMilageRequest() {
        // Create the variables.
        int minMileage = 0;
        int maxMileage = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Ask the user for the minimum mileage.
                System.out.print("Enter minimum mileage: ");
                minMileage = scanner.nextInt();
                
                // Ask the user for the maximum mileage.
                System.out.print("Enter maximum mileage: ");
                maxMileage = scanner.nextInt();
                
                // Set validInput to true to stop the loop.
                validInput = true;
            // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid mileage.");
                // Clear the invalid input.
                scanner.nextLine();
            }
        }
    
        // Print title.
        System.out.println("\nSearch Results:");
    
        // Get vehicles by mileage range
        vehiclesByMileage = dealership.getVehiclesByMilage(minMileage, maxMileage);
        
        // If nothing matches, print error.
        if (vehiclesByMileage.isEmpty()) {
            System.out.println("No vehicles found within the specified mileage range.");
        } else {
            displayVehicles(vehiclesByMileage);
        }
    }

    // Create the processGetByVehicleTypeRequest method.
    public void processGetByVehicleTypeRequest() {
        // Create the variables.
        String vehicleType = "";
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Ask the user for the vehicle type.
                System.out.print("Enter vehicle type: ");
                vehicleType = scanner.nextLine();
                
                // Set validInput to true to stop the loop.
                validInput = true;
            // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid vehicle type.");
                // Clear the invalid input.
                scanner.nextLine();
            }
        }
    
        // Print title.
        System.out.println("\nSearch Results:");
    
        // Get vehicles by vehicle type
        List<Vehicle> vehiclesByType = dealership.getVehiclesByType(vehicleType);
        
        // If nothing matches, print error.
        if (vehiclesByType.isEmpty()) {
            System.out.println("No vehicles found with the specified vehicle type.");
        } else {
            displayVehicles(vehiclesByType);
        }
    }

    // Create the processAddVehicleRequest method.
    public void processAddVehicleRequest() {
        try {
            // Ask user for the VIN.
            System.out.print("Enter VIN: ");
            int vin = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            // Ask user for the year.
            System.out.print("Enter year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            // Ask user for the make.
            System.out.print("Enter make: ");
            String make = scanner.nextLine();
    
            // Ask user for the model.
            System.out.print("Enter model: ");
            String model = scanner.nextLine();
    
            // Ask user for the vehicle type.
            System.out.print("Enter vehicle type: ");
            String vehicleType = scanner.nextLine();
    
            // Ask user for the color.
            System.out.print("Enter color: ");
            String color = scanner.nextLine();
    
            // Ask user for the odometer reading.
            System.out.print("Enter odometer: ");
            int odometer = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            // Ask the user for the price.
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            
            // Create a new vehicle object.
            vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    
            // Add the vehicle to the dealership.
            dealership.addVehicle(vehicle);
    
            // Save the dealership after adding the vehicle
            dealershipFileManager.saveDealership(dealership);
    
            // Print success message.
            System.out.println("\nVehicle added successfully!");
        // Print error if invalid input.
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values for each field.");
        }
    }

    // Create the processRemoveVehicleRequest method.
    public void processRemoveVehicleRequest() {
        try {
            // Ask the user for the VIN.
            System.out.print("Enter VIN of the vehicle to remove: ");
            int vinToRemove = scanner.nextInt();
            scanner.nextLine();
            
            // Find the vehicle in the array.
            Vehicle vehicleToRemove = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vinToRemove) {
                    vehicleToRemove = vehicle;
                    break;
                }
            }
            
            // If no vehicle with a matching VIN found, print message.
            if (vehicleToRemove == null) {
                System.out.println("Vehicle with VIN " + vinToRemove + " not found.");
            } else {
                // Remove the vehicle from the array.
                dealership.removeVehicle(vehicleToRemove);
    
                // Save after removing the vehicle.
                dealershipFileManager.saveDealership(dealership);
    
                // Print success message.
                System.out.println("\nVehicle removed successfully!");
            }
        // Print error if invalid input.
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid VIN.");
        }
    }

    private void sellOrLeaseVehicle() {
        // Print menu.
        System.out.println("\nSell or lease a vehicle:");
        System.out.println("1. Sell");
        System.out.println("2. Lease");

        // Ask user what option they want.
        System.out.print("Enter your choice: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        // Read the user input and execute the appropriate method.
        switch (option) {
            case 1:
                sellVehicle();
                break;
            case 2:
                leaseVehicle();
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }

    // Create getCurrentDateMethod.
    private String getCurrentDate() {
        // Set the format.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return formatter.format(date);
    }

    private void sellVehicle() {
        // Initalize the variable.
        ContractDataManager contractDataManager = new ContractDataManager();
        
        // Ask the user to enter the VIN.
        System.out.print("Enter VIN of the vehicle to sell: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
    
        // Search for the vehicle.
        Vehicle vehicleToSell = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToSell = vehicle;
                break;
            }
        }
        
        // If no results, print message.
        if (vehicleToSell == null) {
            System.out.println("Vehicle with VIN " + vin + " not found.");
            return;
        }
    
        // Ask the user for the customer name.
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        
        // Ask the user for the customer email.
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine();
        
        // Ask the user for the total price.
        System.out.print("Enter total price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();
    
        // Calculate the sales tax.
        double salesTax = totalPrice * SalesContract.salesTaxRate;
    
        // Calculate the processing fee.
        double processingFee = (totalPrice < 10000) ? SalesContract.feeUnder10K : SalesContract.feeOver10K;

        // Call offerAddOns method.
        offerAddOns();
    
        // Create the SalesContract object.
        SalesContract salesContract = new SalesContract(getCurrentDate(), customerName, customerEmail, vehicleToSell, totalPrice, salesTax, processingFee);

        // Add the select AddOns.
        for (AddOn addOn : selectedAddOns) {
            salesContract.addSelectedAddOn(addOn);
        }
    
        // Save the contract.
        contractDataManager.saveContract(salesContract);
    
        // Remove vehicle.
        dealership.removeVehicle(vehicleToSell);
    }

    private void leaseVehicle() {
        // Initalize the variable.
        ContractDataManager contractDataManager = new ContractDataManager();
        
        // Ask the user to enter the VIN.
        System.out.print("Enter VIN of the vehicle to lease: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
    
        // Search for the vehicle.
        Vehicle vehicleToLease = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToLease = vehicle;
                break;
            }
        }
        
        // If no results, print message.
        if (vehicleToLease == null) {
            System.out.println("Vehicle with VIN " + vin + " not found.");
            return;
        }
    
        // Ask the user for the customer name.
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        
        // Ask the user for the customer email.
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine();
        
        // Ask the user for the total price.
        System.out.print("Enter total price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();
    
        // Calculate the lease fee.
        double leaseFee = totalPrice * LeaseContract.leaseFeeRate;
    
        // Calculate the monthly payment.
        double monthlyPayment = totalPrice * LeaseContract.interestRate / (1 - Math.pow(1 + LeaseContract.interestRate, -LeaseContract.loanTerm));
    
        // Create the LeaseContract object.
        LeaseContract leaseContract = new LeaseContract(getCurrentDate(), customerName, customerEmail, vehicleToLease, leaseFee, monthlyPayment);
    
        // Save the contract.
        contractDataManager.saveContract(leaseContract);
    
        // Remove vehicle.
        dealership.removeVehicle(vehicleToLease);
    }

    // Create offerAddOns method.
    private void offerAddOns() {
        // Print menu.
        System.out.println("Select Add-Ons (Enter corresponding numbers separated by comma if multiple):");
        System.out.println("1. Nitrogen tires - $50");
        System.out.println("2. All-season floor mats - $30");
        System.out.println("3. Cargo tray - $40");
        System.out.println("4. Window tinting - $100");
        System.out.println("5. Splash guards - $20");
        System.out.println("6. Wheel locks - $25");
        
        // Ask user what AddOns they want.
        System.out.print("Enter Add-On numbers (0 for none): ");
        String input = scanner.nextLine();
        String[] selections = input.split(",");
        
        // Read the user input and execute the appropriate method.
        for (String selection : selections) {
            int choice = Integer.parseInt(selection.trim());
            switch (choice) {
                case 1:
                    selectedAddOns.add(new AddOn("Nitrogen tires", 50));
                    break;
                case 2:
                    selectedAddOns.add(new AddOn("All-season floor mats", 30));
                    break;
                case 3:
                    selectedAddOns.add(new AddOn("Cargo tray", 40));
                    break;
                case 4:
                    selectedAddOns.add(new AddOn("Window tinting", 100));
                    break;
                case 5:
                    selectedAddOns.add(new AddOn("Splash guards", 20));
                    break;
                case 6:
                    selectedAddOns.add(new AddOn("Wheel locks", 25));
                    break;
                default:
                    break;
            }
        }
    }

    // Create adminLogin method.
    private void adminLogin() {
        // Ask the user for the admin password.
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        
        // If password is admin123, access granted.
        if (password.equals("admin123")) {
            AdminUserInterface adminUI = new AdminUserInterface(new ContractDataManager());
            adminUI.display();
        // If password is incorrect, access denied.
        } else {
            System.out.println("Incorrect password. Access denied.");
        }
    }
}