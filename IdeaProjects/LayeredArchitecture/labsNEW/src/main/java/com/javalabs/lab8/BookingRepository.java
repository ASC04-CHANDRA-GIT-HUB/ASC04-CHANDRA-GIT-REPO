package com.javalabs.lab8;

import java.sql.*;
import java.util.*;

public class BookingRepository {
    private List<BookingModel> bookings = new ArrayList<>();
    private Connection conn;

    public BookingRepository() {
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish connection
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;" +
                            "database=myfriends_db;" +
                            "user=sa;" +
                            "password=sqlserver;" +
                            "encrypt=true;" +
                            "trustServerCertificate=true"
            );

            System.out.println("✅ Connected to SQL Server successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ SQL Server JDBC Driver not found. Make sure the JAR is on the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to SQL Server. Please check database URL, username, and password.");
            e.printStackTrace();
        }
    }

    public void insertBooking(BookingModel b) {
        // Debug info
        System.out.println("DEBUG: conn = " + conn);
        System.out.println("DEBUG: booking = " + b);

        // Null checks
        if (conn == null) {
            System.err.println("❌ Cannot insert booking: Database connection is not available.");
            return;
        }
        if (b == null) {
            System.err.println("❌ Cannot insert booking: BookingModel is null.");
            return;
        }

        // Store in in-memory list
        bookings.add(b);

        // Save in DB
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO bookings " +
                            "(bookingId, source, destination, ticketClass, numPassengers, isRoundTrip) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, b.getBookingId());
            stmt.setString(2, b.getSource());
            stmt.setString(3, b.getDestination());
            stmt.setString(4, b.getTicketClass());
            stmt.setInt(5, b.getNoOfPassengers());
            stmt.setBoolean(6, b.isRoundTrip());

            stmt.executeUpdate();
            System.out.println("✅ Booking inserted successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Error inserting booking into database:");
            e.printStackTrace();
        }
    }

    public List<BookingModel> getAllBookings() {
        return bookings;
    }

    public void loadFromDB() {
        if (conn == null) {
            System.err.println("❌ Cannot load bookings: Database connection is not available.");
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookings");
            bookings.clear();
            while (rs.next()) {
                BookingModel b = new BookingModel(
                        rs.getInt("bookingId"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("ticketClass"),
                        rs.getInt("numPassengers"),
                        rs.getBoolean("isRoundTrip")
                );
                bookings.add(b);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error loading bookings from database:");
            e.printStackTrace();
        }
    }

    public void deleteBooking(int bookingId) {
        bookings.removeIf(b -> b.getBookingId() == bookingId);
        if (conn == null) {
            System.err.println("❌ Cannot delete booking: Database connection is not available.");
            return;
        }
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM bookings WHERE bookingId = ?");
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
            System.out.println("🗑️ Booking deleted successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Error deleting booking:");
            e.printStackTrace();
        }
    }

    public void updateBooking(BookingModel updated) {
        deleteBooking(updated.getBookingId());
        insertBooking(updated);
        System.out.println("✏️ Booking updated successfully.");
    }

    public void updateBooking(int updateId, String newSource) {
        // Implementation can be added later
    }

    public void readBookings() {
        // Implementation can be added later
    }
}
