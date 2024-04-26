/* 
Vehicle.java

This Java file contains the contructor for the vehicle array and has all the getters for other class files to use to get data as needed.
*/

package com.pluralsight;

class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    // Create the getters.
    public int getVin() {
        return vin;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "VIN: " + vin + ", Year: " + year + ", Make: " + make + ", Model: " + model + ", Type: " + vehicleType + ", Color: " + color + ", Odometer: " + odometer + ", Price: $" + price;
    }
}