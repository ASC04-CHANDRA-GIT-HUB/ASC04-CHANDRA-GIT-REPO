package com.JigiJigi.Products;

public class Orders {

    private boolean orderPlaced = false;

    // Place the order
    public void placeOrder() {
        if (!orderPlaced) {
            orderPlaced = true;
            System.out.println("✅ Order placed successfully.");
        } else {
            System.out.println("⚠️ Order has already been placed.");
        }
    }

    // Cancel the order
    public void cancelOrder() {
        if (orderPlaced) {
            orderPlaced = false;
            System.out.println("❌ Order has been canceled.");
        } else {
            System.out.println("⚠️ No order to cancel.");
        }
    }

    // Track the order
    public void trackOrder() {
        if (orderPlaced) {
            System.out.println("📦 Your order is being processed and will be shipped soon.");
        } else {
            System.out.println("ℹ️ No active order to track.");
        }
    }

    // Optional: for internal use
    public boolean isOrderPlaced() {
        return orderPlaced;
    }
}
