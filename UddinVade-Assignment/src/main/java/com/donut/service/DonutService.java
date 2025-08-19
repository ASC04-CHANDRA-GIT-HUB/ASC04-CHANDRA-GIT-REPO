package com.donut.service;
import com.donut.model.Donut;

import java.util.List;
import java.util.function.Function;

public class DonutService {
    private static final double TAX = 10.0;

    // Function to add tax and return new price
    private final Function<Donut, Double> priceWithTax = d -> d.getBasePrice() + TAX;

    // Show all donuts with tax included
    public void displayDonutsWithTax(List<Donut> donuts) {
        System.out.println("Donuts with price (including tax) < ₹100:");
        donuts.forEach(d -> {
            double total = priceWithTax.apply(d);
            System.out.println(d.getName() + " - ₹" + total);
        });
    }

    // Filter and display only donuts < 100 (after tax)
    public void displayFilteredDonutNames(List<Donut> donuts) {
        System.out.println("\nDonuts with price (including tax ) < ₹100:");
        donuts.stream()
                .filter(d -> priceWithTax.apply(d) < 100)
                .map(Donut::getName)
                .forEach(System.out::println);
    }
}
