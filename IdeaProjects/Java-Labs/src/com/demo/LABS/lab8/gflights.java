package com.demo.LABS.lab8;

 
import java.util.Scanner;

public class gflights {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingRepository repo = new BookingRepository();
        int choice;

        do {
            System.out.println("\n=== GFlights Booking System ===");
            System.out.println("1. Add Booking");
            System.out.println("2. View All Bookings");
            System.out.println("3. Update Booking Source");
            System.out.println("4. Delete Booking");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    BookingModel booking = MenuUtil.inputBooking();
                    repo.insertBooking(booking);
                    break;
                case 2:
                    repo.readBookings();
                    break;
                case 3:
                    int updateId = MenuUtil.inputId("update");
                    String newSource = MenuUtil.inputNewSource();
                    repo.updateBooking(updateId, newSource);
                    break;
                case 4:
                    int deleteId = MenuUtil.inputId("delete");
                    repo.deleteBooking(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}


