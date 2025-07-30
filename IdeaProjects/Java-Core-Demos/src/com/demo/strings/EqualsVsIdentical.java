package com.demo.strings;

public class EqualsVsIdentical {

    public static void main(String[] args) {


        String s1 = "yes";
        String s2 = "yes";
        String s3 = new String (s1);
        // checks for equality
        if(s1.equals(s2)) {
            System.out.println("(s1.equals(s2)");
        }
        // identicality
        if(s1 == s2) {
            System.out.println("(s1 == s2)");
        }

        if(s1.equals(s3)) {
            System.out.println("(s1.equals(s3)");
        }

        if(s1 == s3) {
            System.out.println("(s1 == s2)");
        }
    }
}