package com.JigiJigi.Products;


import java.util.*;

public class Cart {

    class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(String name, double price) {
        items.add(new Item(name, price));
        System.out.println(name + " added to cart for ₹" + price);
    }

    public void removeItem(String name) {
        boolean found = false;
        for (Item item : items) {
            if (item.name.equalsIgnoreCase(name)) {
                items.remove(item);
                System.out.println(name + " removed from cart.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(name + " not found in cart.");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.price;
        }
        return total;
    }

    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in cart:");
            for (Item item : items) {
                System.out.println("- " + item.name + " : ₹" + item.price);
            }
        }
    }

    public void clearCart() {
        items.clear();
        System.out.println("All items removed. Cart is now empty.");
    }
}
