package com.demo.exception;

public class handledException{
    public static void main(String args[]){
        int i=10;
        @SuppressWarnings("unused")
        int res=0;
        System.out.println("divide by 0");
        try{
            res=i/0;
            System.out.println("Will this print!?");
            res=i/10;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            System.out.println(exception);
            System.out.println(exception.getMessage());
            System.out.println("Abruptly terminated, so this does not execute");
            System.out.println("Gracefully handled!");
        }
        System.out.println(100);
    }
}