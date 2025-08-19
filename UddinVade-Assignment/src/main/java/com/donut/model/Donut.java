package com.donut.model;

public class Donut {
    private String name;
    private double basePrice;

    public Donut(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
}