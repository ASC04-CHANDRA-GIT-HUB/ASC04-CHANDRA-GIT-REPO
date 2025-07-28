package com.demo.LABS.lab8;

import java.sql.*;
import java.util.*;

public class BookingRepository {
    private List<BookingModel> bookings = new ArrayList<>();
    private Connection conn;

    public BookingRepository() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;database=myfriends_db;user=sa;password=sqlserver;encrypt=true;trustServerCertificate=true"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBooking(BookingModel b) {
        bookings.add(b);
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO bookings (booking_id, source, destination, ticket_class, no_of_passengers, round_trip) VALUES (?, ?, ?, ?, ?, ?)"
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
            e.printStackTrace();
        }
    }

    public List<BookingModel> getAllBookings() {
        return bookings;
    }

    public void loadFromDB() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookings");
            bookings.clear();
            while (rs.next()) {
                BookingModel b = new BookingModel(
                    rs.getInt("booking_id"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("ticket_class"),
                    rs.getInt("no_of_passengers"),
                    rs.getBoolean("round_trip")
                );
                bookings.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(int bookingId) {
        bookings.removeIf(b -> b.getBookingId() == bookingId);
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM bookings WHERE booking_id = ?");
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
            System.out.println("🗑️ Booking deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBooking(BookingModel updated) {
        deleteBooking(updated.getBookingId());
        insertBooking(updated);
        System.out.println("✏️ Booking updated successfully.");
    }

    public void updateBooking(int updateId, String newSource) {
    }

    public void readBookings() {

    }
}
