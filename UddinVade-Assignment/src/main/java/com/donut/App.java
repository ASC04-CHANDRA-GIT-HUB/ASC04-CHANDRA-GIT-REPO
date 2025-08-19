package com.donut;


import com.donut.model.Donut;
import com.donut.service.DonutService;

import java.util.*;

public class App{
    public static void main(String[] args) {
        List<Donut> donuts = List.of(
                new Donut("Plain Donut", 69),
                new Donut("Colorful Donut", 79),
                new Donut("Chocolate Donut", 89)
        );

        DonutService service = new DonutService();
        service.displayDonutsWithTax(donuts);
        service.displayFilteredDonutNames(donuts);
    }
}