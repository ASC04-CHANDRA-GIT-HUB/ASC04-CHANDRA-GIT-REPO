package com.JigiJigi.Products;


public class Payments {

    private boolean paymentStatus = false;

    public void makePayment() {
        if (!paymentStatus) {
            System.out.println("Payment completed successfully.");
            paymentStatus = true;
        } else {
            System.out.println("Payment already made.");
        }
    }

    public void cancelPayment() {
        if (paymentStatus) {
            System.out.println("Payment has been canceled and refunded.");
            paymentStatus = false;
        } else {
            System.out.println("No payment to cancel.");
        }
    }

    public boolean isPaymentDone() {
        return paymentStatus;
    }
}
