package com.demo.LABS.lab8;

import java.util.Scanner;

public class MenuUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static BookingModel inputBooking() {
        System.out.print("Booking ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Source: ");
        String source = sc.nextLine();
        System.out.print("Destination: ");
        String destination = sc.nextLine();
        System.out.print("Ticket Class: ");
        String ticketClass = sc.nextLine();
        System.out.print("No of Passengers: ");
        int count = sc.nextInt();
        System.out.print("Is Round Trip (true/false): ");
        boolean roundTrip = sc.nextBoolean();

        return new BookingModel(id, source, destination, ticketClass, count, roundTrip);
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
