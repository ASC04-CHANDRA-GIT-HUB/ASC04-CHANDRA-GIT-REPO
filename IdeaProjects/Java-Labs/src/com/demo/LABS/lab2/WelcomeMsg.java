package com.demo.LABS.lab2;

public class WelcomeMsg{
    public static String welcomemsg(String name){return("Hello "+name+" welcome to the world! ");}
    public static void main(String[] args){
        String msg=welcomemsg("Boss!");
        System.out.println(msg);
    }
}
