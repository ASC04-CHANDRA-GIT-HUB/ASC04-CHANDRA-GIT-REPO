package com.ecz.ui;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to ECommerce App!");
        MenuUI menuUI = new MenuUI();

        menuUI.accessMenu();
        menuUI.addProduct();
//        menuUI.updateProduct();
        menuUI.getAllProducts();
    }
}
