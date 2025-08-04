package JavaEcomProject.JigiJigi.Products;

import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private final Profile profile = new Profile();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the E-Commerce App!");

        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) profile.login();
        else profile.signUp();

        if (profile.getLoggedInProfileId() == -1) return;

        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. View Wishlist");
            System.out.println("4. Place Order");
            System.out.println("5. View Profile");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: showCategories(); break;
                case 2: Cart.viewCart(profile.getLoggedInProfileId()); break;
                case 3: Wishlist.viewWishlist(profile.getLoggedInProfileId()); break;
                case 4: Orders.placeOrder(profile.getLoggedInProfileId()); break;
                case 5: profile.viewProfile(); break;
                case 0: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private void showCategories() {
        System.out.println("\nCategories:");
        System.out.println("1. Shoes");
        System.out.println("2. Shirts");
        System.out.println("3. Pants");
        System.out.print("Choice: ");
        int ch = Integer.parseInt(scanner.nextLine());
        String category = ch == 1 ? "Shoes" : ch == 2 ? "Shirts" : "Pants";

        List<String[]> products = Product.getProductsByCategory(category);
        for (String[] p : products) {
            System.out.println(p[0] + ". " + p[1] + " - ₹" + p[2]);
        }
        System.out.println("Enter Product ID to:");
        System.out.println("1. Add to Cart");
        System.out.println("2. Add to Wishlist");
        System.out.print("Choice: ");
        int action = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Product ID: ");
        int pid = Integer.parseInt(scanner.nextLine());
        if (action == 1) Cart.addToCart(profile.getLoggedInProfileId(), pid);
        else Wishlist.addToWishlist(profile.getLoggedInProfileId(), pid);
    }
}
