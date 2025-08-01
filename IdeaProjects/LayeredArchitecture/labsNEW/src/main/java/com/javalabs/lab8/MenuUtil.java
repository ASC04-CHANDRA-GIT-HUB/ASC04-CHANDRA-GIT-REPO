package com.javalabs.lab8;

import java.util.Scanner;

public class MenuUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static BookingModel inputBooking() {
        try {
            System.out.print("Booking ID: ");
            int id = sc.nextInt();
            sc.nextLine();  // consume newline

            System.out.print("Source: ");
            String source = sc.nextLine();

            System.out.print("Destination: ");
            String destination = sc.nextLine();

            System.out.print("Ticket Class: ");
            String ticketClass = sc.nextLine();

            System.out.print("No of Passengers: ");
            int count = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Is Round Trip (true/false): ");
            boolean roundTrip = Boolean.parseBoolean(sc.nextLine().trim());  // safer than sc.nextBoolean()

            return new BookingModel(id, source, destination, ticketClass, count, roundTrip);

        } catch (Exception e) {
            System.out.println("⚠️ Invalid input! Error: " + e.getMessage());
            return null;
        }
    }


    public static int inputId(String action) {
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Booking ID to " + action + ": ");
        return s.nextInt();
    }

    public static String inputNewSource() {
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        System.out.print("Enter new source: ");
        return s.nextLine();
    }
}
