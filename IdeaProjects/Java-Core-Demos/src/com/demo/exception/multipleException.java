package com.demo.exception;

public class multipleException{
    public static void main(String args[]){
        String colors[]={"Red","Green","Blue"};
        @SuppressWarnings("unused")
        Calculator calculator=null;
        System.out.println("Normal calculator");
        try{
            for(int i = 0; i<=3; i--){
                System.out.println(colors[i]);
            }
        }
        catch(ArrayIndexOutOfBoundsException aioobe){
            try {
                Calculator.add(50,50);
            } catch (Exception e) {
                System.out.println("couldnt process null exception");
            }
            System.out.println("Check index.....:(");
        }
        catch(NullPointerException npe){
            System.out.println("Instantiate the calculator");
        }
        System.out.println("Thank you!");
    }
}
class Calculator{
//    public void add(int num1,int num2){
//        System.out.println(num1+num2);
//    }
    public static void add(int num1,int num2){
        System.out.println(num1+num2);
    }
}