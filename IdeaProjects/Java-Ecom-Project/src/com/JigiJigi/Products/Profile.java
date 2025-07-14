package com.JigiJigi.Products;


import java.util.*;

public class Profile {

    private String name;
    private String email;

    public Profile() {
        this.name = "Guest";
        this.email = "guest@shop.com";
    }

    public void createProfile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter your email: ");
        this.email = scanner.nextLine();

        System.out.println("Profile created successfully!");
    }

    public void viewProfile() {
        System.out.println("------ Profile Info ------");
        System.out.println("Name : " + name);
        System.out.println("Email: " + email);
    }

    public void updateName(String newName) {
        this.name = newName;
        System.out.println("Name updated to: " + name);
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
        System.out.println("Email updated to: " + email);
    }

    public void deleteProfile() {
        this.name = "Guest";
        this.email = "guest@shop.com";
        System.out.println("Profile has been reset.");
    }
}
