package com.yearup.dealership;

public class AddOn {
    private String name;
    private double price;

    public AddOn(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
