package com.demo.LABS.lab1;
 class overload{
     public int add(int a,int b){
         return a+b;
     }
     public int add(int a,int b, int c){
         return a+b+c;
     }
     public double add(double a, double b){
         return a+b;
     }

     public String add(String a,int b) {
         return a+" "+b;
     }
     public static void main(String args[]){
         overload obj=new overload();
         System.out.println(obj.add(10,20));
         System.out.println(obj.add(10,20,30));
         System.out.println(obj.add(10.5,20.1));
         System.out.println(obj.add("Hello",20));
     }
 }