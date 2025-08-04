package JavaEcomProject.JigiJigi.Products;

public class Offers {

    public void showAvailableOffers() {
        System.out.println("🎁 Available Offers:");
        System.out.println("1. 10% off for orders above ₹2000");
        System.out.println("2. ₹500 off for orders above ₹5000");
    }

    public double getBestOffer(double total) {
        if (total >= 5000) {
            return total - 500;
        } else if (total >= 2000) {
            return total * 0.9;
        }
        return total;
    }
}
