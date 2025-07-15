package com.JigiJigi.Products;

import java.util.*;

public class ShoppingCart {

    Cart cart = new Cart();
    Offers offers = new Offers();
    Orders orders = new Orders();
    Wishlist wishlist = new Wishlist();
    Payments payments = new Payments();
    Profile profile = new Profile();
    Scanner scanner = new Scanner(System.in);

    public void startShopping() {
        System.out.println("🛒 Welcome to the Shopping App!\n");

        profile.createProfile();

        cart.addItem("Shoes", 2499);
        cart.addItem("Watch", 1299);

        int choice;
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Offers");
            System.out.println("2. Wishlist");
            System.out.println("3. Orders");
            System.out.println("4. Payments");
            System.out.println("5. Cart");
            System.out.println("6. Profile");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    offersMenu();
                    break;
                case 2:
                    wishlistMenu();
                    break;
                case 3:
                    ordersMenu();
                    break;
                case 4:
                    paymentsMenu();
                    break;
                case 5:
                    cartMenu();
                    break;
                case 6:
                    profileMenu();
                    break;
                case 0:
                    System.out.println("👋 Exiting. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);
    }

    private void offersMenu() {
        double total = cart.calculateTotal();
        System.out.println("\n--- OFFERS MENU ---");
        System.out.println("Cart total: ₹" + total);
        offers.showAvailableOffers();
        double discounted = offers.getBestOffer(total);
        System.out.println("Total after best offer: ₹" + discounted);
    }

    private void wishlistMenu() {
        int ch;
        do {
            System.out.println("\n--- WISHLIST MENU ---");

            if (wishlistIsEmpty()) {
                System.out.println("Your wishlist is empty.");
                System.out.print("Would you like to add an item? (yes/no): ");
                String ans = scanner.nextLine().trim().toLowerCase();
                if (ans.equals("yes") || ans.equals("y")) {
                    addItemToWishlist();
                } else {
                    return;
                }
            }

            System.out.println("1. Add to Wishlist");
            System.out.println("2. Remove from Wishlist");
            System.out.println("3. View Wishlist");
            System.out.println("4. Clear Wishlist");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            ch = Integer.parseInt(scanner.nextLine());

            switch (ch) {
                case 1:
                    addItemToWishlist();
                    break;
                case 2:
                    if (!wishlistIsEmpty()) {
                        System.out.print("Enter item name to remove: ");
                        wishlist.removeFromWishlist(scanner.nextLine());
                    } else {
                        System.out.println("Wishlist is empty.");
                    }
                    break;
                case 3:
                    wishlist.listWishlist();
                    break;
                case 4:
                    wishlist.clearWishlist();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (ch != 0);
    }

    private boolean wishlistIsEmpty() {
        return wishlist.getSize() == 0;
    }

    private void addItemToWishlist() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());
        wishlist.addToWishlist(name, price);
    }

    private void ordersMenu() {
        int ch;
        do {
            System.out.println("\n--- ORDERS MENU ---");
            System.out.println("1. Place Order");
            System.out.println("2. Cancel Order");
            System.out.println("3. Track Order");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            ch = Integer.parseInt(scanner.nextLine());

            switch (ch) {
                case 1:
                    orders.placeOrder();
                    break;
                case 2:
                    orders.cancelOrder();
                    break;
                case 3:
                    orders.trackOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (ch != 0);
    }

    private void paymentsMenu() {
        int ch;
        do {
            System.out.println("\n--- PAYMENTS MENU ---");
            System.out.println("1. Make Payment");
            System.out.println("2. Cancel Payment");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            ch = Integer.parseInt(scanner.nextLine());

            switch (ch) {
                case 1:
                    payments.makePayment();
                    break;
                case 2:
                    payments.cancelPayment();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (ch != 0);
    }

    private void cartMenu() {
        int ch;
        do {
            System.out.println("\n--- CART MENU ---");
            System.out.println("1. View Cart Items");
            System.out.println("2. Remove Item");
            System.out.println("3. Clear Cart");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            ch = Integer.parseInt(scanner.nextLine());

            switch (ch) {
                case 1:
                    cart.listItems();
                    break;
                case 2:
                    System.out.print("Enter item name to remove: ");
                    cart.removeItem(scanner.nextLine());
                    break;
                case 3:
                    cart.clearCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (ch != 0);
    }

    private void profileMenu() {
        int ch;
        do {
            System.out.println("\n--- PROFILE MENU ---");
            System.out.println("1. View Profile");
            System.out.println("2. Update Name");
            System.out.println("3. Update Email");
            System.out.println("4. Reset Profile");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            ch = Integer.parseInt(scanner.nextLine());

            switch (ch) {
                case 1:
                    profile.viewProfile();
                    break;
                case 2:
                    System.out.print("Enter new name: ");
                    profile.updateName(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new email: ");
                    profile.updateEmail(scanner.nextLine());
                    break;
                case 4:
                    profile.deleteProfile();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (ch != 0);
    }
}
