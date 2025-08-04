package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;

public class Wishlist {
    public static void addToWishlist(int profileId, int productId) {
        String sql = "INSERT INTO Wishlist (ProfileID, ProductID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profileId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            System.out.println("✅ Added to wishlist.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewWishlist(int profileId) {
        String sql = "SELECT p.ProductName, p.Price FROM Wishlist w JOIN Products p ON w.ProductID = p.ProductID WHERE w.ProfileID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profileId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\n💖 Your Wishlist:");
            while (rs.next()) {
                System.out.println(rs.getString("ProductName") + " - ₹" + rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
