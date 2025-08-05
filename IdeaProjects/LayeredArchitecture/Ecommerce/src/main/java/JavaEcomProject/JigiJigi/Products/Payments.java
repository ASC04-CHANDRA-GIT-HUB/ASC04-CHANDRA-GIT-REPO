package JavaEcomProject.JigiJigi.Products;

import java.util.Scanner;

public class Payments {
    private final Scanner scanner = new Scanner(System.in);

    public String selectPaymentMethod() {
        System.out.println("\n=== PAYMENT OPTIONS ===");
        System.out.println("1. Cash on Delivery (COD)");
        System.out.println("2. UPI");
        System.out.println("3. Credit/Debit Card");
        System.out.print("Choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                return "COD";
            case "2":
                return enterUPIDetails();
            case "3":
                return enterCardDetails();
            default:
                System.out.println("❌ Invalid choice.");
                return selectPaymentMethod();
        }
    }

    private String enterUPIDetails() {
        System.out.print("Enter UPI ID: ");
        String upi = scanner.nextLine();
        return "UPI: " + upi;
    }

    private String enterCardDetails() {
        System.out.print("Enter Card Number: ");
        String cardNo = scanner.nextLine();
        System.out.print("Enter Expiry (MM/YY): ");
        String expiry = scanner.nextLine();
        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();
        return "Card: " + cardNo + " Exp: " + expiry;
    }
}
