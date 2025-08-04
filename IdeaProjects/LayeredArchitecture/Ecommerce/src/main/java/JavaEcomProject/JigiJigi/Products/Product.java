package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    public static List<String[]> getProductsByCategory(String category) {
        List<String[]> products = new ArrayList<>();
        String sql = "SELECT ProductID, ProductName, Price FROM Products WHERE Category = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new String[]{
                        String.valueOf(rs.getInt("ProductID")),
                        rs.getString("ProductName"),
                        String.valueOf(rs.getDouble("Price"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
