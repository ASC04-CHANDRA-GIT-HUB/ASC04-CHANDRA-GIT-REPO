package com.demo.debugging;

public class DebuggingTools {

    static public void main(String args[]){
        salaryCalculator();
    }
    public static void  salaryCalculator(){
        int baseSalary = 1000;
        double tooMuchBonus =  rakhiBonus()+ baseSalary;
        System.out.println(tooMuchBonus);
    }
    public static double  rakhiBonus(){
        int rakhi = 1000;
        int i = 100/0;
        int sweets = -500;
        return rakhi+sweets + randomBonus();
    }
    public static double  randomBonus(){
        return Math.random() + 100;
    }
}