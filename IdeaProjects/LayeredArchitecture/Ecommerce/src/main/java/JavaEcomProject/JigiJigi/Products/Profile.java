package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class Profile {
    private int loggedInProfileId = -1;
    private final Scanner scanner = new Scanner(System.in);

    public int getLoggedInProfileId() {
        return loggedInProfileId;
    }

    public void signUp() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            String password, confirm;
            while (true) {
                System.out.print("Enter password: ");
                password = scanner.nextLine();
                System.out.print("Re-enter password: ");
                confirm = scanner.nextLine();
                if (password.equals(confirm)) break;
                System.out.println("❌ Passwords do not match. Try again.");
            }

            String sql = "INSERT INTO Profile (Name, Email, Password) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    loggedInProfileId = rs.getInt(1);
                    System.out.println("✅ Account created & logged in!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String sql = "SELECT ProfileID FROM Profile WHERE Email = ? AND Password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    loggedInProfileId = rs.getInt("ProfileID");
                    System.out.println("✅ Logged in successfully!");
                } else {
                    System.out.println("❌ No account found. Signing up...");
                    signUp();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewProfile() {
        if (loggedInProfileId == -1) {
            System.out.println("⚠ Please log in first.");
            return;
        }
        String sql = "SELECT Name, Email FROM Profile WHERE ProfileID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, loggedInProfileId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("👤 Name: " + rs.getString("Name"));
                System.out.println("📧 Email: " + rs.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
