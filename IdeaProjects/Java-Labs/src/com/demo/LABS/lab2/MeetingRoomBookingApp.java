package com.demo.LABS.lab2;

import java.time.*;
import java.util.*;

class MeetingRoom {
    String roomId;
    int capacity, floor;
    boolean hasZoom;
    String zoomDeviceId, zoomAccountId;

    public MeetingRoom(String roomId, int capacity, int floor, boolean hasZoom,
                       String zoomDeviceId, String zoomAccountId) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.floor = floor;
        this.hasZoom = hasZoom;
        this.zoomDeviceId = zoomDeviceId;
        this.zoomAccountId = zoomAccountId;
    }

    void display() {

        if (hasZoom) {
            System.out.println("ZOOM MEETINGS");
            System.out.println("Room ID: " + roomId + ", Capacity: " + capacity + " Zoom Device ID: " + zoomDeviceId + ", Zoom Account ID: " + zoomAccountId);
        }else{
            System.out.println("Room ID: " + roomId + ", Capacity: " + capacity + ", Floor: " + floor);
        }
    }
}

class Booking {
    String employeeId, roomId;
    LocalDate date;
    LocalTime time;
    int duration;

    public Booking(String employeeId, String roomId, LocalDate date, LocalTime time, int duration) {
        this.employeeId = employeeId;
        this.roomId = roomId;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    void display() {
        System.out.println("Employee: " + employeeId + ", Room: " + roomId +
                ", Date: " + date + ", Time: " + time + ", Duration: " + duration + " mins");
    }
}

public class MeetingRoomBookingApp {
    static List<MeetingRoom> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        rooms.add(new MeetingRoom("MR101", 10, 2, false, null, null));
        rooms.add(new MeetingRoom("MR202", 8, -1, true, "ZD123", "zoom456"));
        rooms.add(new MeetingRoom("MR303", 7, -1, true, "AZ345", "zoom458"));
        rooms.add(new MeetingRoom("MR404", 20, -1, true, "DZ123", "zoom457"));

        System.out.println("All Meeting Rooms:");
        for (MeetingRoom r : rooms) r.display();

        bookings.add(new Booking("EMP001", "MR101", LocalDate.of(2025, 7, 15), LocalTime.of(10, 0), 60));
        bookings.add(new Booking("EMP002", "MR202", LocalDate.of(2025, 7, 15), LocalTime.of(14, 30), 45));
        bookings.add(new Booking("EMP003", "MR303", LocalDate.of(2025, 7, 16), LocalTime.of(11, 0), 50));
        bookings.add(new Booking("EMP004", "MR404", LocalDate.of(2025, 7, 16), LocalTime.of(15, 30), 75));


        LocalDate searchDate = LocalDate.of(2025, 7, 16);
        System.out.println("\nBookings on " + searchDate + ":");
        for (Booking b : bookings) {
            if (b.date.equals(searchDate)) b.display();
        }
    }
}
