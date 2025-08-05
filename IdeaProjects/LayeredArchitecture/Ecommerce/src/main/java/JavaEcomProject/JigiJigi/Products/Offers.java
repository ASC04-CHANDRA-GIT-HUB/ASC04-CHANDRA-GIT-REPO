package JavaEcomProject.JigiJigi.Products;

public class Offers {
    public void showAvailableOffers() {
        System.out.println("\n--- Available Offers ---");
        System.out.println("1. 10% off on orders above ₹5000");
        System.out.println("2. 5% off on Shoes category");
        System.out.println("3. No current coupon codes.");
    }

    public double getBestOffer(double total, String category) {
        double discount = 0;

        if (total > 5000) {
            discount = total * 0.10;
        } else if ("Shoes".equalsIgnoreCase(category)) {
            discount = total * 0.05;
        }
        return total - discount;
    }
}
