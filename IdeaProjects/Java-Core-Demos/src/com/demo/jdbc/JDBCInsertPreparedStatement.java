package com.demo.jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBCInsertPreparedStatement {
    public static void main(String[] args) {
        Connection connection = null;
        // The PreparedStatement interface is used to execute parameterized SQL queries.
        // The PreparedStatement is more efficient than Statement for executing the same query multiple times with different parameters.
        // It is effficient because the same query is compiled once and executed many times.
        PreparedStatement preparedStatement = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Loaded!");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;user=sa;password=sqlserver;trustServerCertificate=true;";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection established!");

            // String insertQuery = "INSERT INTO Users (user_id, email, last_name) VALUES (?, ?, ?)";
            String insertQuery = "INSERT INTO Users (user_id, email, last_name) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);

            // Accept one row from the user
            System.out.print("Enter User ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();

            // Insert the user-provided row
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, lastName);
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");

            // Loop to insert 100 additional rows
            for (int i = 1000; i <= 1100; i++) {
                int newUserId = userId + i;
                String generatedEmail = "user" + newUserId + "@example.com";
                String generatedLastName = lastName + i;

                preparedStatement.setInt(1, newUserId);
                preparedStatement.setString(2, generatedEmail);
                preparedStatement.setString(3, generatedLastName);

                preparedStatement.executeUpdate();
            }

            System.out.println("100 additional rows inserted with auto-generated IDs.");

        } catch (Exception exception) {
            System.err.println("Error: " + exception.getMessage());
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
                scanner.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}