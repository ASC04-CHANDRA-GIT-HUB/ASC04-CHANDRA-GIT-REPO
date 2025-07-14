package com.JigiJigi.Products;
import java.util.*;

public class Wishlist {

    class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private List<Item> wishlistItems;

    public Wishlist() {
        wishlistItems = new ArrayList<>();
    }

    public void addToWishlist(String name, double price) {
        wishlistItems.add(new Item(name, price));
        System.out.println(name + " added to wishlist for ₹" + price);
    }

    public void removeFromWishlist(String name) {
        boolean found = false;
        for (Item item : wishlistItems) {
            if (item.name.equalsIgnoreCase(name)) {
                wishlistItems.remove(item);
                System.out.println(name + " removed from wishlist.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(name + " not found in wishlist.");
        }
    }

    public int getSize() {
        return wishlistItems.size();
    }

    public void listWishlist() {
        if (wishlistItems.isEmpty()) {
            System.out.println("Wishlist is empty.");
        } else {
            System.out.println("Items in wishlist:");
            for (Item item : wishlistItems) {
                System.out.println("- " + item.name + " : ₹" + item.price);
            }
        }
    }

    public void clearWishlist() {
        wishlistItems.clear();
        System.out.println("All items removed. Wishlist is now empty.");
    }
}
