package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;

public class Orders {
    public static void placeOrder(int profileId) {
        String sql = "INSERT INTO Orders (ProfileID, TotalAmount) " +
                "SELECT ?, SUM(p.Price) FROM Cart c JOIN Products p ON c.ProductID = p.ProductID WHERE c.ProfileID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profileId);
            pstmt.setInt(2, profileId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                clearCart(profileId);
                System.out.println("✅ Order placed successfully!");
            } else {
                System.out.println("⚠ Cart is empty!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearCart(int profileId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Cart WHERE ProfileID = ?")) {
            pstmt.setInt(1, profileId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
