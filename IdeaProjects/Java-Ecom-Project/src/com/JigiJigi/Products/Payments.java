package com.JigiJigi.Products;


public class Payments {

    private boolean paymentStatus = false;

    // Process the payment
    public void makePayment() {
        if (!paymentStatus) {
            System.out.println("Payment completed successfully.");
            paymentStatus = true;
        } else {
            System.out.println("Payment already made.");
        }
    }

    // Cancel the payment
    public void cancelPayment() {
        if (paymentStatus) {
            System.out.println("Payment has been canceled and refunded.");
            paymentStatus = false;
        } else {
            System.out.println("No payment to cancel.");
        }
    }

    // Optional: check status
    public boolean isPaymentDone() {
        return paymentStatus;
    }
}
