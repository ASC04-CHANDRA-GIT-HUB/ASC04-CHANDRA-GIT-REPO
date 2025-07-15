package com.demo.LABS.lab2;

public class Largest {
    public static int largest(int a, int b, int c) {
        int large=a;
        if(b>c){
            if(b>a)large=b;
        }
        else{if (c>a)large=c;}
        return large;
    }
    public static void main(String args[]){
            int res=largest(28,13,5);
        System.out.println("The largest number amongst is "+res);
    }
}
