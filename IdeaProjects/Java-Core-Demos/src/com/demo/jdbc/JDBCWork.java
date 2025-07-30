package com.demo.jdbc;

import java.sql.*;

public class JDBCWork {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Loaded!");

            // Use Windows Authentication
            // String url =
            // "jdbc:sqlserver://localhost:1433;databaseName=TestDB;integratedSecurity=true;";
            // String url =
            // "jdbc:sqlserver://localhost:1433;databaseName=TestDB;user=sa;password=sqlserver;";
            // jdbc is the protocol used to connect to the database
            // sqlserver is the subprotocol used for SQL Server
            // localhost is the server name
            // 1433 is the default port for SQL Server
            // TestDB is the name of the database
            // user is the username to connect to the database
            // password is the password for the user
            // trustServerCertificate=true is used to bypass SSL certificate validation
            String url = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;user=sa;password=sqlserver;trustServerCertificate=true;";
            // DriverManager.getConnection() method is used to establish a connection to the database using the provided URL, username, and password.
            connection = DriverManager.getConnection(url);
            System.out.println("Connection established!");
            // The Statement interface is used to execute SQL statements against a database.
            Statement statement = connection.createStatement();
            System.out.println("Statement created");
            // The executeQuery() method is used to execute a SQL SELECT statement and returns a ResultSet object.
            // The ResultSet interface provides methods to retrieve data from the result set returned by a SQL query.
            // The ResultSet object allows you to iterate through the rows of the result set and retrieve
            ResultSet resultSet =statement.executeQuery("SELECT * FROM Users where user_id=1 or user_id=2");
            System.out.println("Result set returned");
            System.out.println(resultSet);
            // Prints SQLServerResultSet:1 because the result set is an instance of SQLServerResultSet class.
            // The next() method moves the cursor to the next row of the result set.
            while(resultSet.next())// Move the cursor to the first row of the result set
            {
                int userId =  resultSet.getInt("user_id");
                System.out.println("User ID: " + userId);
                String email = resultSet.getString("email");
                System.out.println("Email: " + email);
                String lastName = resultSet.getString(3);
                System.out.println("Last Name: " + lastName);
            }
//            resultSet =statement.executeQuery("SELECT * FROM Products");
//            System.out.println("Result set returned");
//            System.out.println(resultSet);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}