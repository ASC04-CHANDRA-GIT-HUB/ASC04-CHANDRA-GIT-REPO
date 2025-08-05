package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;
import java.util.*;

public class ShoppingCart {
    private final Profile profile;
    private final Scanner scanner = new Scanner(System.in);

    public ShoppingCart(Profile profile) {
        this.profile = profile;
    }

    public void startShopping() {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Profile");
            System.out.println("2. Browse Products");
            System.out.println("3. Cart");
            System.out.println("4. Wishlist");
            System.out.println("5. Orders");
            System.out.println("6. Address Book");
            System.out.println("7. Payment");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    profileMenu();
                    break;
                case "2":
                    browseProducts();
                    break;
                case "3":
                    cartMenu();
                    break;
                case "4":
                    wishlistMenu();
                    break;
                case "5":
                    ordersMenu();
                    break;
                case "6":
                    new AddressBook(profile).selectOrAddAddress();
                    break;
                case "7":
                    new Payments().selectPaymentMethod();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void profileMenu() {
        System.out.println("\n=== PROFILE ===");
        profile.viewProfile();
    }

    private void browseProducts() {
        List<String> categories = Product.getCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        System.out.println("\n=== CATEGORIES ===");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.print("Select category: ");
        int catChoice = Integer.parseInt(scanner.nextLine());
        if (catChoice < 1 || catChoice > categories.size()) {
            System.out.println("❌ Invalid category.");
            return;
        }

        String selectedCategory = categories.get(catChoice - 1);
        List<Product> products = Product.getProductsByCategory(selectedCategory);
        if (products.isEmpty()) {
            System.out.println("No products available in this category.");
            return;
        }

        for (Product p : products) {
            System.out.println(p.getProductId() + ". " + p.getProductName() + " - ₹" + p.getPrice());
        }
        System.out.print("Enter Product ID to add to cart or 0 to go back: ");
        int pid = Integer.parseInt(scanner.nextLine());
        if (pid != 0) {
            addToCart(pid);
        }
    }

    private void cartMenu() {
        if (isCartEmpty()) {
            System.out.println("🛒 Your cart is empty.");
            return;
        }

        while (true) {
            System.out.println("\n=== CART MENU ===");
            viewCart();
            System.out.println("1. Remove Item");
            System.out.println("2. Clear Cart");
            System.out.println("3. Place Order");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    removeFromCart();
                    break;
                case "2":
                    clearCart();
                    break;
                case "3":
                    placeOrderFromCart();
                    return;
                case "0":
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void wishlistMenu() {
        System.out.println("\n(Wishlist feature here - similar to cart)");
    }

    private void ordersMenu() {
        Orders orders = new Orders(profile);
        System.out.println("\n1. Order History");
        System.out.println("2. Cancel Order");
        System.out.print("Choice: ");
        String choice = scanner.nextLine();

        if ("1".equals(choice)) {
            orders.viewOrders();
        } else if ("2".equals(choice)) {
            System.out.print("Enter Order ID: ");
            int oid = Integer.parseInt(scanner.nextLine());
            orders.cancelOrder(oid);
        }
    }

    private boolean isCartEmpty() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM Cart WHERE ProfileID = ?");
            stmt.setInt(1, profile.getProfileId());
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            return true;
        }
    }

    private void viewCart() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT p.ProductName, c.Quantity, p.Price FROM Cart c JOIN Products p ON c.ProductID = p.ProductID WHERE c.ProfileID = ?");
            stmt.setInt(1, profile.getProfileId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("ProductName") + " x" + rs.getInt("Quantity") + " - ₹" + rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addToCart(int productId) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Cart (ProfileID, ProductID, Quantity) VALUES (?, ?, 1)");
            stmt.setInt(1, profile.getProfileId());
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            System.out.println("✅ Added to cart.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeFromCart() {
        System.out.print("Enter Product ID to remove: ");
        int pid = Integer.parseInt(scanner.nextLine());
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cart WHERE ProfileID = ? AND ProductID = ?");
            stmt.setInt(1, profile.getProfileId());
            stmt.setInt(2, pid);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Removed.");
            else System.out.println("❌ Not found in cart.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearCart() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cart WHERE ProfileID = ?");
            stmt.setInt(1, profile.getProfileId());
            stmt.executeUpdate();
            System.out.println("🗑 Cart cleared.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void placeOrderFromCart() {
        AddressBook ab = new AddressBook(profile);
        int addrId = ab.selectOrAddAddress();
        Payments pay = new Payments();
        String paymentDetails = pay.selectPaymentMethod();
        new Orders(profile).placeOrder(addrId, paymentDetails.split(":")[0], paymentDetails);
    }
}
