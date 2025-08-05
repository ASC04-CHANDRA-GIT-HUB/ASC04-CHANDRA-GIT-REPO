package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;

public class Orders {
    private final Profile profile;

    public Orders(Profile profile) {
        this.profile = profile;
    }

    public void placeOrder(int addressId, String paymentMethod, String paymentDetails) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            String orderSQL = "INSERT INTO Orders (ProfileID, AddressID, PaymentMethod, PaymentDetails, Status) VALUES (?, ?, ?, ?, 'Pending')";
            PreparedStatement orderStmt = conn.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, profile.getProfileId());
            orderStmt.setInt(2, addressId);
            orderStmt.setString(3, paymentMethod);
            orderStmt.setString(4, paymentDetails);
            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            String cartItemsSQL = "SELECT ProductID, Quantity FROM Cart WHERE ProfileID = ?";
            PreparedStatement cartStmt = conn.prepareStatement(cartItemsSQL);
            cartStmt.setInt(1, profile.getProfileId());
            ResultSet cartRs = cartStmt.executeQuery();

            String orderItemsSQL = "INSERT INTO OrderItems (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
            PreparedStatement orderItemsStmt = conn.prepareStatement(orderItemsSQL);

            while (cartRs.next()) {
                orderItemsStmt.setInt(1, orderId);
                orderItemsStmt.setInt(2, cartRs.getInt("ProductID"));
                orderItemsStmt.setInt(3, cartRs.getInt("Quantity"));
                orderItemsStmt.addBatch();
            }
            orderItemsStmt.executeBatch();

            String clearCartSQL = "DELETE FROM Cart WHERE ProfileID = ?";
            PreparedStatement clearCartStmt = conn.prepareStatement(clearCartSQL);
            clearCartStmt.setInt(1, profile.getProfileId());
            clearCartStmt.executeUpdate();

            conn.commit();
            System.out.println("✅ Order placed successfully! Order ID: " + orderId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewOrders() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT OrderID, Status, PaymentMethod, OrderDate FROM Orders WHERE ProfileID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, profile.getProfileId());
            ResultSet rs = pstmt.executeQuery();

            boolean empty = true;
            System.out.println("\n--- ORDER HISTORY ---");
            while (rs.next()) {
                empty = false;
                System.out.println("Order ID: " + rs.getInt("OrderID") +
                        " | Status: " + rs.getString("Status") +
                        " | Payment: " + rs.getString("PaymentMethod") +
                        " | Date: " + rs.getTimestamp("OrderDate"));
            }
            if (empty) {
                System.out.println("📦 No previous orders found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelOrder(int orderId) {
        if (!orderExists(orderId)) {
            System.out.println("❌ Order ID not found.");
            return;
        }
        if (isOrderDelivered(orderId)) {
            System.out.println("🚫 Cannot cancel a delivered order.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Orders SET Status = 'Cancelled' WHERE OrderID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
            System.out.println("✅ Order cancelled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean orderExists(int orderId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT 1 FROM Orders WHERE OrderID = ? AND ProfileID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, profile.getProfileId());
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean isOrderDelivered(int orderId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT Status FROM Orders WHERE OrderID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return "Delivered".equalsIgnoreCase(rs.getString("Status"));
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
