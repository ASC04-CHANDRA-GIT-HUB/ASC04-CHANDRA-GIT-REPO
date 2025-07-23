package com.demo.jdbc;
//
//public class JDBCDel {
//    public static void main(String[] args) {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            System.out.println("Driver Loaded!");
//
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=myFriendsDb;user=sa;password=sqlserver;trustServerCert";
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class JDBCDel {
    public static void main(String[] args) {

        Connection connection = null;

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Loaded!");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=myfriends_db;user=sa;password=sqlserver;encrypt=true;trustServerCertificate=true;";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection Established!");

            Statement statement = connection.createStatement();
            System.out.println("Statement is Created!");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM friends");
            System.out.println("\nResultSet returned");

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println("\nID: " + id);
                String friendname = resultSet.getString("friendname");
                System.out.println("Friend name: " + friendname);
                String hobbies = resultSet.getString(3);
                System.out.println("Hobbies: " + hobbies);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
