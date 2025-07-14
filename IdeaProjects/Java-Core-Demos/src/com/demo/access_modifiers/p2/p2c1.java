package com.demo.access_modifiers.p2;

import com.demo.access_modifiers.p1.p1c1;

public class p2c1 extends p1c1{
    public static void main(String args[]) {
        p2c1 p2C1 = new p2c1();
//        System.out.println(p1C1.privateVar);
//        p1C1.privateMethod();
//        System.out.println(p1C1.defaultVar);
//        p1C1.defaultMethod();
        System.out.println(p2C1.protectedVar);
        p2C1.protectedMethod();
    }
}