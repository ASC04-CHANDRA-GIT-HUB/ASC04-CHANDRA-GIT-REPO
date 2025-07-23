package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCInsertStatement {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Loaded!");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=myfriends_db;user=sa;password=sqlserver;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection Established!");

            Statement stmt = conn.createStatement();
            System.out.println("Statement is created!");

            while (true) {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Add Friend");
                System.out.println("2. View Friends");
                System.out.println("3. Update Friend");
                System.out.println("4. Delete Friend");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Full Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email Address: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        long phone = Long.parseLong(scanner.nextLine());

                        String insertQuery = "INSERT INTO friendsTst VALUES (?, ?, ?, ?)";
                        PreparedStatement psInsert = conn.prepareStatement(insertQuery);
                        psInsert.setInt(1, id);
                        psInsert.setString(2, name);
                        psInsert.setString(3, email);
                        psInsert.setLong(4, phone);
                        psInsert.executeUpdate();
                        System.out.println("Friend Added Successfully!");
                        break;

                    case 2:
                        String selectQuery = "SELECT * FROM friendsTst";
                        ResultSet rs = stmt.executeQuery(selectQuery);
                        System.out.println("ID\tName\t\tEmail\t\t\tPhone");
                        while (rs.next()) {
                            System.out.printf("%d\t%s\t\t%s\t\t%d\n",
                                    rs.getInt("ID"),
                                    rs.getString("fullName"),
                                    rs.getString("emailAddr"),
                                    rs.getLong("phoneNumber"));
                        }
                        break;

                    case 3:
                        System.out.print("Enter ID to Update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Enter New Phone: ");
                        long newPhone = Long.parseLong(scanner.nextLine());

                        String updateQuery = "UPDATE friendsTst SET emailAddr = ?, phoneNumber = ? WHERE ID = ?";
                        PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
                        psUpdate.setString(1, newEmail);
                        psUpdate.setLong(2, newPhone);
                        psUpdate.setInt(3, updateId);
                        int rowsUpdated = psUpdate.executeUpdate();
                        if (rowsUpdated > 0)
                            System.out.println("Friend Updated Successfully!");
                        else
                            System.out.println("No Friend Found with ID: " + updateId);
                        break;

                    case 4:
                        System.out.print("Enter ID to Delete: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());

                        String deleteQuery = "DELETE FROM friendsTst WHERE ID = ?";
                        PreparedStatement psDelete = conn.prepareStatement(deleteQuery);
                        psDelete.setInt(1, deleteId);
                        int rowsDeleted = psDelete.executeUpdate();
                        if (rowsDeleted > 0)
                            System.out.println("Friend Deleted Successfully!");
                        else
                            System.out.println("No Friend Found with ID: " + deleteId);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        conn.close();
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
