package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class Profile {
    private int profileId;
    private String name;
    private String email;

    private final Scanner sc = new Scanner(System.in);

    public int getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Login or Signup flow
    public boolean loginOrSignup() {
        while (true) {
            System.out.println("\n=== Welcome to E-Commerce ===");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                if (login()) return true;
            } else if (choice.equals("2")) {
                signup();
                return true;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Login logic
    private boolean login() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Email: ");
            String inputEmail = sc.nextLine().trim();

            // Check if user exists
            String sqlCheck = "SELECT ProfileID, Name, Password FROM Profile WHERE Email = ?";
            PreparedStatement ps = conn.prepareStatement(sqlCheck);
            ps.setString(1, inputEmail);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("⚠️ No account found for this email.");
                System.out.println("Redirecting to Signup...");
                signup();
                return true;
            }

            int dbProfileId = rs.getInt("ProfileID");
            String dbName = rs.getString("Name");
            String dbPassword = rs.getString("Password");

            System.out.print("Enter Password: ");
            String inputPassword = sc.nextLine().trim();

            if (dbPassword.equals(inputPassword)) {
                this.profileId = dbProfileId;
                this.name = dbName;
                this.email = inputEmail;
                System.out.println("✅ Login successful! Welcome back, " + dbName);
                return true;
            } else {
                System.out.println("❌ Incorrect password.");
                System.out.print("Try again? (y/n): ");
                if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                    return login(); // retry
                }
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Signup logic
    private void signup() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Name: ");
            String inputName = sc.nextLine().trim();

            System.out.print("Enter Email: ");
            String inputEmail = sc.nextLine().trim();

            // Check if email already exists
            String checkSql = "SELECT 1 FROM Profile WHERE Email = ?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, inputEmail);
            ResultSet checkRs = checkPs.executeQuery();
            if (checkRs.next()) {
                System.out.println("⚠️ Email already registered. Please login.");
                login();
                return;
            }

            // Ask for password twice
            String password;
            while (true) {
                System.out.print("Enter Password: ");
                String pass1 = sc.nextLine().trim();
                System.out.print("Re-enter Password: ");
                String pass2 = sc.nextLine().trim();
                if (!pass1.equals(pass2)) {
                    System.out.println("❌ Passwords do not match. Try again.");
                } else {
                    password = pass1;
                    break;
                }
            }

            // Insert new profile
            String insertSql = "INSERT INTO Profile (Name, Email, Password) VALUES (?, ?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            insertPs.setString(1, inputName);
            insertPs.setString(2, inputEmail);
            insertPs.setString(3, password);
            insertPs.executeUpdate();

            ResultSet rs = insertPs.getGeneratedKeys();
            if (rs.next()) {
                this.profileId = rs.getInt(1);
            }
            this.name = inputName;
            this.email = inputEmail;

            System.out.println("✅ Signup successful! Welcome, " + name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Profile
    public void viewProfile() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT Name, Email FROM Profile WHERE ProfileID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, profileId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- PROFILE ---");
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Email: " + rs.getString("Email"));
            } else {
                System.out.println("⚠️ Profile not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
