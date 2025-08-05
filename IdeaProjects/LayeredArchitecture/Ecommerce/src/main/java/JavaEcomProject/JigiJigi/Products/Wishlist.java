package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;

public class Wishlist {
    private final Profile profile;

    public Wishlist(Profile profile) {
        this.profile = profile;
    }

    public void addToWishlist(int productId) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Wishlist (ProfileID, ProductID) VALUES (?, ?)"
            );
            stmt.setInt(1, profile.getProfileId());
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            System.out.println("✅ Added to wishlist.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT 1 FROM Wishlist WHERE ProfileID = ?"
            );
            stmt.setInt(1, profile.getProfileId());
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            return true;
        }
    }

    public void viewWishlist() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT w.WishlistID, p.ProductName, p.Price FROM Wishlist w JOIN Products p ON w.ProductID = p.ProductID WHERE w.ProfileID = ?"
            );
            stmt.setInt(1, profile.getProfileId());
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n--- WISHLIST ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("WishlistID") + " | " + rs.getString("ProductName") + " = ₹" + rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
