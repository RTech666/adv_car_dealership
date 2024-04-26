/* 
DealershipFileManager.java

This Java file contains all the information that allows the other classes to read the CSV and adjust it's contents.
*/

package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class DealershipFileManager {
    // Create the variables.
    public Dealership dealership;
    public DealershipFileManager dealershipFileManager;
    public String[] dealershipInfo;
    public String name;
    public String address;
    public String phone;
    public String[] vehicleData;
    public int vin;
    public int year;
    public String make;
    public String model;
    public String vehicleType;
    public String color;
    public int odometer;
    public double price;
    public Vehicle vehicle;

    // Set the CSV file.
    public static String INVENTORY_FILE_PATH = "inventory.csv";

    public Dealership getDealership() {
        dealership = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE_PATH))) {
            String line;
            // Read the first line to get dealership information.
            dealershipInfo = reader.readLine().split("\\|");

            // Seperate the information from the first line.
            name = dealershipInfo[0];
            address = dealershipInfo[1];
            phone = dealershipInfo[2];

            // Create dealership object.
            dealership = new Dealership(name, address, phone);

            // Read vehicle information
            while ((line = reader.readLine()) != null) {
                vehicleData = line.split("\\|");

                // Seperate the vehicle information.
                vin = Integer.parseInt(vehicleData[0]);
                year = Integer.parseInt(vehicleData[1]);
                make = vehicleData[2];
                model = vehicleData[3];
                vehicleType = vehicleData[4];
                color = vehicleData[5];
                odometer = Integer.parseInt(vehicleData[6]);
                price = Double.parseDouble(vehicleData[7]);

                // Create vehicle object and add to dealership inventory.
                vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
        // If any error occurs, print error.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dealership;
    }

    // Create saveDealerShip method.
    public void saveDealership(Dealership dealership) {
        // Write the information to the CSV.
        try (FileWriter writer = new FileWriter("inventory.csv")) {
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}