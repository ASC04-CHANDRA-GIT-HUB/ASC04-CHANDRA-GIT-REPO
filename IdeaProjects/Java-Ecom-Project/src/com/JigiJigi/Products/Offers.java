package com.JigiJigi.Products;

public class Offers {

    public double applyFlatDiscount(double total) {
        if (total >= 1000) {
            System.out.println("Flat ₹100 discount applied.");
            return total - 100;
        } else {
            System.out.println("Flat discount not applicable for total below ₹1000.");
            return total;
        }
    }

    public double applyPercentageDiscount(double total, double percent) {
        double discount = (percent / 100) * total;
        System.out.println(percent + "% discount applied: ₹" + discount);
        return total - discount;
    }

    public double getBestOffer(double total) {
        double flat = applyFlatDiscount(total);
        double percent = applyPercentageDiscount(total, 10);

        if (flat < percent) {
            System.out.println("Best offer: Flat ₹100 off.");
            return flat;
        } else {
            System.out.println("Best offer: 10% off.");
            return percent;
        }
    }

    public void showAvailableOffers() {
        System.out.println("Available Offers:");
        System.out.println("- Flat ₹100 off on orders above ₹1000");
        System.out.println("- 10% off on all orders");
    }
}
