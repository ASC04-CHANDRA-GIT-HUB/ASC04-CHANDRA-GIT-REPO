package com.demo.LABS.lab2;

import java.util.*;
public class TOstring{
    public static void printDigitsInWords(int number) {
        String[] words = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String numStr = String.valueOf(number);
        for (char digit : numStr.toCharArray()) {
            System.out.print(words[digit - '0'] + " ");
        }
    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        System.out.print("Enter a number: ");
        int a=sc.nextInt();
        if(a>99 && a<1000){
            System.out.print("In words: ");
            printDigitsInWords(a);
        }else{
            System.out.println("Invalid! Enter a 3 digit number.");
        }
    }
}