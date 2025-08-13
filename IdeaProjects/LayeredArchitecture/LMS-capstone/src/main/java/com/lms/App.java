package com.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Enables Spring Boot auto-configuration & component scanning
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("✅ Kannada LMS Backend Started Successfully!");
    }
}
