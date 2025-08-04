package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;

public class Payments {

    public void makePayment() {
        String sql = "INSERT INTO Payments (amount, status) SELECT SUM(price), 'Paid' FROM Cart";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("💳 Payment successful!");
            } else {
                System.out.println("No items in cart to pay for.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelPayment() {
        String sql = "UPDATE Payments SET status = 'Cancelled' WHERE status = 'Paid'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("❌ Payment cancelled.");
            } else {
                System.out.println("No payments to cancel.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
