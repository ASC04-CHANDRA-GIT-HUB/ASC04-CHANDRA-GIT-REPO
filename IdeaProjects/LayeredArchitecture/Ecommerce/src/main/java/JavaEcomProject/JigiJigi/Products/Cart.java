package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;

public class Cart {
    public static void addToCart(int profileId, int productId) {
        String sql = "INSERT INTO Cart (ProfileID, ProductID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profileId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            System.out.println("✅ Added to cart.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewCart(int profileId) {
        String sql = "SELECT p.ProductName, p.Price FROM Cart c JOIN Products p ON c.ProductID = p.ProductID WHERE c.ProfileID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profileId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\n🛒 Your Cart:");
            while (rs.next()) {
                System.out.println(rs.getString("ProductName") + " - ₹" + rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
