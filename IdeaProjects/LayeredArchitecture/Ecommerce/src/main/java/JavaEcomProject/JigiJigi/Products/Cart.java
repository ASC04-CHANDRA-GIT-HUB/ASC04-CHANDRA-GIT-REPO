package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;

public class Cart {
    private final Profile profile;

    public Cart(Profile profile) {
        this.profile = profile;
    }

    public void addToCart(int productId, int qty) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Cart (ProfileID, ProductID, Quantity) VALUES (?, ?, ?)"
            );
            stmt.setInt(1, profile.getProfileId());
            stmt.setInt(2, productId);
            stmt.setInt(3, qty);
            stmt.executeUpdate();
            System.out.println("✅ Added to cart.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT 1 FROM Cart WHERE ProfileID = ?"
            );
            stmt.setInt(1, profile.getProfileId());
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            return true;
        }
    }

    public void viewCart() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT c.CartID, p.ProductName, c.Quantity, p.Price FROM Cart c JOIN Products p ON c.ProductID = p.ProductID WHERE c.ProfileID = ?"
            );
            stmt.setInt(1, profile.getProfileId());
            ResultSet rs = stmt.executeQuery();
            double total = 0;
            System.out.println("\n--- CART ---");
            while (rs.next()) {
                double price = rs.getDouble("Price") * rs.getInt("Quantity");
                System.out.println("ID: " + rs.getInt("CartID") + " | " + rs.getString("ProductName") + " x" + rs.getInt("Quantity") + " = ₹" + price);
                total += price;
            }
            System.out.println("Total: ₹" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
