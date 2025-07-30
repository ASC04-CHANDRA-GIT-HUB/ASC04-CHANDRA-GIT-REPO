package com.ecz.model;

public class Product {
    //What is a model?
    //A model is a class that represents the data in your application.
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()==0) {
            RuntimeException nameInvalidException = new RuntimeException("The name cannot be empty!");
            throw nameInvalidException;
        }

    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //What is @Override?
    //The @Override annotation is used to indicate that a method is overriding a method in the superclass.
    @Override
    //What is the toString method?
    //The toString method is a method that returns a string representation of an object.
    public String toString() {
        return "Product Details : " +
                "id = " + id + "\t" +
                "name = " + name + "\t" +
                "price = " + price + "\t" +
                "quantity = " + quantity;
    }
}