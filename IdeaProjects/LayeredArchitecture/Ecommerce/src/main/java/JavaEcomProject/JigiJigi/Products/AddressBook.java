package JavaEcomProject.JigiJigi.Products;

import JavaEcomProject.JigiJigi.dbconn.DBConnection;
import java.sql.*;
import java.util.*;

public class AddressBook {
    private final Profile profile;
    private final Scanner scanner;

    public AddressBook(Profile profile) {
        this.profile = profile;
        this.scanner = new Scanner(System.in);
    }

    // Select an address or add a new one
    public int selectOrAddAddress() {
        try (Connection conn = DBConnection.getConnection()) {
            List<Integer> addressIds = new ArrayList<>();

            String sql = "SELECT AddressID, FullName, Street, City, State, ZipCode, Country, Phone " +
                    "FROM Address WHERE ProfileID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, profile.getProfileId());
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n=== ADDRESS BOOK ===");
            int index = 1;
            while (rs.next()) {
                int addressId = rs.getInt("AddressID");
                addressIds.add(addressId);
                System.out.println(index++ + ". " + rs.getString("FullName") +
                        " | " + rs.getString("Street") +
                        ", " + rs.getString("City") +
                        ", " + rs.getString("State") +
                        " - " + rs.getString("ZipCode") +
                        " | " + rs.getString("Country") +
                        " | 📞 " + rs.getString("Phone"));
            }

            System.out.println(index + ". ➕ Add New Address");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == index) {
                return addAddress();
            } else if (choice > 0 && choice < index) {
                return addressIds.get(choice - 1);
            } else {
                System.out.println("❌ Invalid choice. Returning to menu.");
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Add a new address
    public int addAddress() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n=== ADD NEW ADDRESS ===");
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Street: ");
            String street = scanner.nextLine();
            System.out.print("City: ");
            String city = scanner.nextLine();
            System.out.print("State: ");
            String state = scanner.nextLine();
            System.out.print("Zip Code: ");
            String zipCode = scanner.nextLine();
            System.out.print("Country: ");
            String country = scanner.nextLine();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            String insertSQL = "INSERT INTO Address (ProfileID, FullName, Street, City, State, ZipCode, Country, Phone) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, profile.getProfileId());
            pstmt.setString(2, fullName);
            pstmt.setString(3, street);
            pstmt.setString(4, city);
            pstmt.setString(5, state);
            pstmt.setString(6, zipCode);
            pstmt.setString(7, country);
            pstmt.setString(8, phone);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int newAddressId = rs.getInt(1);
                System.out.println("✅ Address added successfully!");
                return newAddressId;
            }
            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
