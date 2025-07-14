package com.demo.exception;

public class unhandledException{
    public static void main(String args[]){
        int i=100;

        System.out.println("divide by 0");

        int res=i/0;
        System.out.println("Will this print!?");
        int res2=i/10;

        System.out.println("Abruptly terminated, so this does not execute");
        System.out.println(res);
        System.out.println(res2);
    }
}