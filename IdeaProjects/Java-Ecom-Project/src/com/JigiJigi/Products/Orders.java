package com.JigiJigi.Products;

public class Orders {

    private boolean orderPlaced = false;

    public void placeOrder() {
        if (!orderPlaced) {
            orderPlaced = true;
            System.out.println("✅ Order placed successfully.");
        } else {
            System.out.println("⚠️ Order has already been placed.");
        }
    }

    public void cancelOrder() {
        if (orderPlaced) {
            orderPlaced = false;
            System.out.println("❌ Order has been canceled.");
        } else {
            System.out.println("⚠️ No order to cancel.");
        }
    }

    public void trackOrder() {
        if (orderPlaced) {
            System.out.println("📦 Your order is being processed and will be shipped soon.");
        } else {
            System.out.println("ℹ️ No active order to track.");
        }
    }

    public boolean isOrderPlaced() {
        return orderPlaced;
    }
}
